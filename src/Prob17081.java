public class Prob17081 {

    interface SlotInfo {}

    static class Monster implements SlotInfo {
        String name;
        int atk, def, health, exp, maxHealth;
        Monster(String a, int b, int c, int d, int e) {
            name = a;
            atk = b;
            def = c;
            health = d;
            maxHealth = d;
            exp = e;
        }
    }

    static class Equipment implements SlotInfo {
        boolean isWeapon;
        int num;
        Equipment(boolean a, int b) {
            isWeapon = a;
            num = b;
        }
    }

    static class Accessory implements SlotInfo {
        int effect;
        Accessory(String a) {
            if(a.equals("HR")) effect = 0;
            else if(a.equals("RE")) effect = 1;
            else if(a.equals("CO")) effect = 2;
            else if(a.equals("EX")) effect = 3;
            else if(a.equals("DX")) effect = 4;
            else if(a.equals("HU")) effect = 5;
            else effect = 6;
        }
    }

    static class Player {
        int health, atk, def, level, exp, equip_accessories;
        Equipment weapon, armor;
        boolean[] accessory;

        Player() {
            health = 20;
            atk = 2;
            def = 2;
            level = 1;
            exp = 0;
            equip_accessories = 0;
            accessory = new boolean[7];
        }

        void print(StringBuilder out) {
            String result = String.format("LV : %d\nHP : %d/%d\nATT : %d+%d\nDEF : %d+%d\nEXP : %d/%d", level, Math.max(0, health), (15+5*level), atk, weapon == null ? 0 : weapon.num, def, armor == null ? 0 : armor.num, exp, 5*level);
            out.append(result).append('\n');
        }

        void earnExp(int earn) {
            if( (exp += earn) >= level*5 ) {
                level += 1;
                atk += 2;
                def += 2;
                health = (15+5*level);
                exp = 0;
            }
        }

        int getDamage(boolean isFirst) {
            int Multiplier = (isFirst && accessory[2]) ? (accessory[4] ? 3 : 2) : 1;
            return (weapon == null) ? atk * Multiplier : (atk + weapon.num) * Multiplier;
        }

        int getDefence() {
            return (armor == null) ? def : def + armor.num;
        }

        boolean revival() {
            if(accessory[1]) {
                accessory[1] = false;
                equip_accessories--;
                health = (15 + 5 * level);
                return true;
            }
            return false;
        }
    }

    static class Game {
        char[][] Map;
        char[] Command;
        SlotInfo[][] Infos;
        int turn, playerX, playerY, sx, sy;
        String gameStatus;
        Player player;
        boolean isTrap;


        Game(char[][] map, char[] cmd, int startX, int startY) {
            this.Map = map;
            this.Command = cmd;
            this.player = new Player();
            sx = startX;
            sy = startY;
            playerX = startX;
            playerY = startY;
            Infos = new SlotInfo[Map.length][Map[0].length];
            turn = 0;
            gameStatus = "Playing";
        }

        void SetInfo(int y, int x, SlotInfo info) {
            Infos[y-1][x-1] = info;
        }

        void Simulate() {
            while (turn < Command.length && gameStatus.equals("Playing")) {

                byte[] dir = Move(Command[turn++]);
                int nx = playerX + dir[0], ny = playerY + dir[1];

                if(!isOutOfGrid(nx, ny) && Map[ny][nx] != '#') {
                    if(Map[playerY][playerX] == '@') Map[playerY][playerX] = '.';
                    playerX = nx; playerY = ny;
                }

                char CurSlot = Map[playerY][playerX];
                if(CurSlot == '^') {
                    if( (player.health -= (player.accessory[4] ? 1 : 5)) <= 0 ) {
                        if(player.revival()) {
                            playerY = sy;
                            playerX = sx;
                            Map[playerY][playerX] = '@';
                        } else {
                            gameStatus = "SPIKE TRAP";
                        }
                    }
                } else if(CurSlot == 'B') {
                    if(Infos[playerY][playerX] instanceof Equipment) {
                        Equipment equip = (Equipment) Infos[playerY][playerX];
                        if(equip.isWeapon) player.weapon = equip;
                        else player.armor = equip;
                    } else {
                        Accessory accessory = (Accessory) Infos[playerY][playerX];
                        if(player.equip_accessories < 4 && !player.accessory[accessory.effect]) {
                            player.accessory[accessory.effect] = true;
                            player.equip_accessories++;
                        }
                    }
                    Map[playerY][playerX] = '@';
                } else if(CurSlot == '&' || CurSlot == 'M') {

                    Monster mob = (Monster) Infos[playerY][playerX];
                    int fightTurn = 0;
                    if(CurSlot == 'M' && player.accessory[5]) player.health = (15 + 5 * player.level);

                    while(fightTurn++ >= 0) {
                        if(fightTurn % 2 == 1) mob.health -= Math.max(1, player.getDamage(fightTurn == 1) - mob.def);
                        else player.health -= (fightTurn == 2 && CurSlot == 'M' && player.accessory[5]) ? 0 : Math.max(1, mob.atk - player.getDefence());

                        if(player.health <= 0) {

                            if(player.revival()) {
                                playerY = sy;
                                playerX = sx;
                                Map[playerY][playerX] = '@';

                                mob.health = mob.maxHealth;
                            } else {
                                gameStatus = mob.name;
                            }

                            break;
                        } else if(mob.health <= 0) {

                            player.earnExp(player.accessory[3] ? (int) Math.floor(mob.exp * 1.2d) : mob.exp);
                            if(player.accessory[0]) player.health = Math.min(15 + 5 * player.level, player.health + 3);

                            Map[playerY][playerX] = '@';

                            if(CurSlot == 'M') gameStatus = "End";
                            break;
                        }
                    }
                } else if(CurSlot == '.') {
                    Map[playerY][playerX] = '@';
                }
            }
            if(Map[playerY][playerX] == '^' && player.health > 0) Map[playerY][playerX] = '@';

            StringBuilder out = new StringBuilder();
            for(char[] carr : Map) out.append(carr).append('\n');
            out.append("Passed Turns : ").append(turn).append('\n');
            player.print(out);

            if(gameStatus.equals("Playing")) out.append("Press any key to continue.");
            else if(gameStatus.equals("End")) out.append("YOU WIN!");
            else out.append("YOU HAVE BEEN KILLED BY ").append(gameStatus).append("..");
            System.out.print(out);
        }

        final byte[][] move = new byte[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        byte[] Move(char c) {
            if (c == 'R') return move[0];
            if (c == 'L') return move[1];
            if (c == 'U') return move[2];
            return move[3];
        }

        boolean isOutOfGrid(int x, int y) {
            return x < 0 || x >= Map[0].length || y < 0 || y >= Map.length;
        }
    }

    static StringBuilder strInput;

    public static void main(String[] args) throws Exception {
        strInput = new StringBuilder();
        char[][] map = new char[read()][read()];
        int x = 0, y = 0, mobs = 0, items = 0, sx = 0, sy = 0;
        while(y < map.length) {

            char c = (char) System.in.read();
            map[y][x++] = c;
            if(c == '&' || c == 'M') mobs++;
            else if(c == 'B') items++;
            else if(c == '@') {
                sx = x-1; sy = y;
            }

            if(x >= map[y].length) {
                y++;
                x=0;
                System.in.read();
            }
        }

        Game game = new Game(map, readCommand().toCharArray(), sx, sy);
        while(mobs-- > 0) game.SetInfo(read(), read(), new Monster(readCommand(), read(), read(), read(), read()));
        while(items-- > 0) {
            int r = read(), c = read();
            char t = readCommand().charAt(0);
            if(t == 'O') {
                game.SetInfo(r, c, new Accessory(readCommand()));
            } else {
                game.SetInfo(r, c, new Equipment(t == 'W', read()));
            }
        }

        game.Simulate();

    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }

    static String readCommand() throws Exception {
        strInput.setLength(0);
        int b;
        while((b=System.in.read())>32) strInput.append((char) b);
        return strInput.toString();
    }
}
