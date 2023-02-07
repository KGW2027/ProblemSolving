import java.util.Arrays;

public class Prob12094 {

    static class Game {
        byte[][] board;
        short[] blocks;
        byte turn, max;
        Game(int R){
            board = new byte[R][R];
            turn = 0;
            blocks = new short[20];
        }

        Game(byte[][] board, short[] blocks, byte t, byte m) {
            this.board = new byte[board.length][];
            this.blocks = blocks.clone();
            for(int k = 0 ; k < board.length ; k++) this.board[k] = board[k].clone();
            this.turn = (++t);
            this.max = m;
        }

        public Game clone() {
            return new Game(board, blocks, turn, max);
        }

        int getMaxMax() {
            return max + (10 - turn);
        }

        boolean canMore() {
            for(short s : blocks) if(s > 1) return true;
            return false;
        }

        void right() {
            for(byte[] b : board) {
                for(byte idx = (byte) (b.length-1) ; idx > 0 ; idx--) {
                    byte here = b[idx];
                    byte grab = grab_lr(b, idx, false);
                    if(grab == -1) break;

                    if(here == 0) {

                        b[idx] = b[grab];
                        b[grab] = 0;
                        idx++;

                    } else if(here == b[grab]){

                        if(++b[idx] > max) max = b[idx];
                        blocks[b[grab]]--;
                        blocks[b[idx]]++;
                        b[grab] = 0;

                        grab = grab_lr(b, idx, false);
                        if(grab == -1) break;
                        if(idx-1 != grab) {
                            b[idx - 1] = b[grab];
                            b[grab] = 0;
                        }

                    }

                }
            }
        }

        void left() {
            for(byte[] b : board) {
                for(byte idx = 0 ; idx < b.length ; idx++) {
                    byte here = b[idx];
                    byte grab = grab_lr(b, idx, true);
                    if(grab == -1) break;

                    if(here == 0) {
                        b[idx] = b[grab];
                        b[grab] = 0;
                        idx--;
                    } else if(here == b[grab]){
                        if(++b[idx] > max) max = b[idx];
                        blocks[b[grab]]--;
                        blocks[b[idx]]++;
                        b[grab] = 0;

                        grab = grab_lr(b, idx, true);
                        if(grab == -1) break;
                        if(grab != idx+1) {
                            b[idx + 1] = b[grab];
                            b[grab] = 0;
                        }
                    }
                }
            }
        }

        void up() {
            for(byte x = 0 ; x < board[0].length ; x++) {
                for(byte y = 0 ; y < board.length ; y++) {
                    byte here = board[y][x];
                    byte grab = grab_ud(x, y, true);
                    if(grab == -1) break;

                    if(here == 0) {
                        board[y][x] = board[grab][x];
                        board[grab][x] = 0;
                        y--;
                    } else if(here == board[grab][x]) {
                        if(++board[y][x] > max) max = board[y][x];
                        blocks[board[grab][x]]--;
                        blocks[board[y][x]]++;
                        board[grab][x] = 0;

                        grab = grab_ud(x, y, true);
                        if(grab == -1) break;
                        if(grab != y+1) {
                            board[y+1][x] = board[grab][x];
                            board[grab][x] = 0;
                        }
                    }
                }
            }
        }

        void down() {
            for(byte x = 0 ; x < board[0].length ; x++) {
                for(byte y = (byte)(board.length-1) ; y >= 0 ; y--) {
                    byte here = board[y][x];
                    byte grab = grab_ud(x, y, false);
                    if(grab == -1) break;

                    if(here == 0) {
                        board[y][x] = board[grab][x];
                        board[grab][x] = 0;
                        y++;
                    } else if(here == board[grab][x]) {
                        if(++board[y][x] > max) max = board[y][x];
                        blocks[board[grab][x]]--;
                        blocks[board[y][x]]++;
                        board[grab][x] = 0;

                        grab = grab_ud(x, y, false);
                        if(grab == -1) break;
                        if(grab != y-1) {
                            board[y-1][x] = board[grab][x];
                            board[grab][x] = 0;
                        }
                    }
                }
            }
        }

        byte grab_ud(byte x, byte start, boolean isUp) {
            if (isUp) {
                while (++start < board.length) if(board[start][x] > 0) return start;
            } else {
                while (--start >= 0) if(board[start][x] > 0) return start;
            }
            return -1;
        }

        byte grab_lr(byte[] arr, byte x, boolean isLeft) {
            if(isLeft) {
                while(++x < arr.length) if(arr[x] > 0) return x;
            } else {
                while(--x >= 0) if(arr[x] > 0) return x;
            }
            return -1;
        }
    }

    static int MAX;
    static int CUR_MAX;

    public static void main(String[] args) throws Exception {
        int R = read(), max = 0, x = 0, y = 0;
        Game game = new Game(R);
        while(y < R) {
            int num = read();
            game.board[y][x++] = (num == 0) ? 0 : (byte) (Math.log(num) / Math.log(2));
            if(game.board[y][x-1] > 0) {
                game.blocks[game.board[y][x-1]]++;
                max = Math.max(max, game.board[y][x - 1]);
            }
            if(x >= R) {
                x = 0;
                y++;
            }
        }
        game.max = (byte) max;
        MAX = game.getMaxMax();
        game.blocks = java.util.Arrays.copyOf(game.blocks, MAX);
        CUR_MAX = game.max;
        simulate(game);
        System.out.print(1 << CUR_MAX);
    }

    static void simulate(Game game) {
        if(game.turn >= 10 || !game.canMore() || game.getMaxMax() <= CUR_MAX || CUR_MAX == MAX)  {
            return;
        }

        Game down = game.clone();
        down.down();
        CUR_MAX = Math.max(down.max, CUR_MAX);
        simulate(down);

        Game up = game.clone();
        up.up();
        CUR_MAX = Math.max(up.max, CUR_MAX);
        simulate(up);

        Game left = game.clone();
        left.left();
        CUR_MAX = Math.max(left.max, CUR_MAX);
        simulate(left);

        Game right = game.clone();
        right.right();
        CUR_MAX = Math.max(right.max, CUR_MAX);
        simulate(right);
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
