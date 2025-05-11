package edu.grinnell.csc207.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * this class store all rooms in the map
 */
public class Rooms {
    private int height;
    private int width;
    private Scanner scanner;
    private int playerpos;
    private int prevpos;
    private Room[] rooms;

    /**
     * tell if there is room in the north of current index room
     * 
     * @param index
     * @return if there is room in the north of current index room
     */
    private boolean validNorth(int index) {
        return ((0 <= (index - width) && (index - width) < rooms.length)
                && (rooms[index - width].isEmpty()));
    }

    /**
     * tell if there is room in the south of current index room
     * 
     * @param index
     * @return if there is room in the south of current index room
     */
    private boolean validSouth(int index) {
        return ((0 <= (index + width) && (index + width) < rooms.length)
                && (rooms[index + width].isEmpty()));
    }

    /**
     * tell if there is room in the west of current index room
     * 
     * @param index
     * @return if there is room in the west of current index room
     */
    private boolean validWest(int index) {
        return ((0 <= (index - 1) && (index - 1) < rooms.length) && (rooms[index - 1].isEmpty())
                && ((index % width) != 0));
    }

    /**
     * tell if there is room in the east of current index room
     * 
     * @param index
     * @return if there is room in the east of current index room
     */
    private boolean validEast(int index) {
        return ((0 <= (index + 1) && (index + 1) < rooms.length) && (rooms[index + 1].isEmpty())
                && ((index % width) != (width - 1)));
    }

    /**
     * tell if north can have a new room or not
     * 
     * @param index
     * @return if north can have a new room or not
     */
    private boolean testNorth(int index) {
        return ((0 <= (index - width) && (index - width) < rooms.length)
                && (!rooms[index - width].isEmpty()));
    }

    /**
     * tell if south can have a new room or not
     * 
     * @param index
     * @return if south can have a new room or not
     */
    private boolean testSouth(int index) {
        return ((0 <= (index + width) && (index + width) < rooms.length)
                && (!rooms[index + width].isEmpty()));
    }

    /**
     * tell if west can have a new room or not
     * 
     * @param index
     * @return if west can have a new room or not
     */
    private boolean testWest(int index) {
        return ((0 <= (index - 1) && (index - 1) < rooms.length) && (!rooms[index - 1].isEmpty())
                && ((index % width) != 0));
    }

    /**
     * tell if east can have a new room or not
     * 
     * @param index
     * @return if east can have a new room or not
     */
    private boolean testEast(int index) {
        return ((0 <= (index + 1) && (index + 1) < rooms.length) && (!rooms[index + 1].isEmpty())
                && ((index % width) != (width - 1)));
    }

    /**
     * count number of non-empty room in map
     * 
     * @return number of non empty room in map
     */
    private int countValidRooms() {
        int ret = 0;
        for (int iter = 0; iter < rooms.length; iter++) {
            if (!rooms[iter].isEmpty()) {
                ret += 1;
            }
        }
        return ret;
    }

    /**
     * set all rooms to empty room
     */
    private void cleanRoom() {
        for (int iter = 0; iter < rooms.length; iter++) {
            rooms[iter] = new Emptyroom();
        }
    }

    /**
     * set tunnels connects to nearby rooms for a room in given index
     * 
     * @param index
     */
    private void setTunnel(int index) {
        if (testNorth(index)) {
            rooms[index].changeNorth(true);
        }

        if (testSouth(index)) {
            rooms[index].changeSouth(true);
        }

        if (testWest(index)) {
            rooms[index].changeWest(true);
        }

        if (testEast(index)) {
            rooms[index].changeEast(true);
        }
    }

    /**
     * RoomPair record the index of a room and number of rooms it is connecting to
     */
    private record RoomPair(int count, int roompos) {
    };

    /**
     * Instantiate rooms
     * 
     * @param scanner
     * @param height
     * @param width
     */
    public Rooms(Scanner scanner, int height, int width) {
        this.height = height;
        this.width = width;
        this.rooms = new Room[height * width];
        cleanRoom();

        int mid = height / 2 * width + width / 2;
        rooms[mid] = new InitialRoom(scanner);
        playerpos = mid;

        do {
            cleanRoom();
            rooms[mid] = new InitialRoom(scanner);
            spawnRoomH(mid, true);
        } while (countValidRooms() != (rooms.length * 3 / 4));

        Random rand = new Random();
        for (int iter = 0; iter < rooms.length; iter++) {
            if (!rooms[iter].isEmpty() && iter != mid) {
                int selectRoom = rand.nextInt(10);
                if (selectRoom <= 1) {
                    rooms[iter] = new SpecialEventRoom(scanner);
                    continue;
                }
                if (selectRoom <= 2) {
                    rooms[iter] = new ShopRoom(scanner);
                    continue;
                }
                rooms[iter] = new FightRoom(scanner);
            }
        }

        for (int iter = 0; iter < rooms.length; iter++) {
            if (!rooms[iter].isEmpty()) {
                setTunnel(iter);
            }
        }
        List<RoomPair> roomPairs = new ArrayList<>();
        for (int iter = 0; iter < rooms.length; iter++) {
            if (!rooms[iter].isEmpty() && iter != mid) {
                if (roomPairs.size() == 0) {
                    roomPairs.add(new RoomPair(rooms[iter].getConnectionNum(), iter));
                    continue;
                }
                if ((roomPairs.get(0).count) > (rooms[iter].getConnectionNum())) {
                    roomPairs = new ArrayList<>();
                    roomPairs.add(new RoomPair(rooms[iter].getConnectionNum(), iter));
                    continue;
                }
                if ((roomPairs.get(0).count) == (rooms[iter].getConnectionNum())) {
                    roomPairs.add(new RoomPair(rooms[iter].getConnectionNum(), iter));
                }
            }
        }
        int roomCount = rand.nextInt(roomPairs.size());
        int bossPos = roomPairs.get(roomCount).roompos;
        rooms[bossPos] = new BossRoom(scanner);
        setTunnel(bossPos);
    }

    /**
     * Help to spawn room
     * 
     * @param currentpos
     * @param initial
     */
    public void spawnRoomH(int currentpos, boolean initial) {
        Random rand = new Random();
        if (!initial) {
            rooms[currentpos] = new Testroom(scanner);
        }
        if (rand.nextInt(4) <= 1) {
            if (validWest(currentpos)) {
                spawnRoomH(currentpos - 1, false);
            }
        }

        if (rand.nextInt(4) <= 1) {
            if (validEast(currentpos)) {
                spawnRoomH(currentpos + 1, false);
            }
        }

        if (rand.nextInt(4) <= 1) {
            if (validNorth(currentpos)) {
                spawnRoomH(currentpos - width, false);
            }
        }

        if (rand.nextInt(4) <= 1) {
            if (validSouth(currentpos)) {
                spawnRoomH(currentpos + width, false);
            }
        }
    }

    /**
     * tell if a certain column exist room
     * 
     * @param col
     * @return if a certain column exist room
     */
    public boolean colExistRoom(int col) {
        boolean roomExist = false;
        for (int iter = 0; iter < height; iter++) {
            if ((!rooms[(iter * width) + col].isEmpty())
                    && rooms[(iter * width) + col].isPresent()) {
                roomExist = true;
                break;
            }
        }
        return roomExist;
    }

    /**
     * tell if a certain row exist room
     * 
     * @param row
     * @return if a certain row exist room
     */
    public boolean rowExistRoom(int row) {
        boolean roomExist = false;
        for (int iter = 0; iter < height; iter++) {
            if ((!rooms[(row * width) + iter].isEmpty())
                    && rooms[(row * width) + iter].isPresent()) {
                roomExist = true;
                break;
            }
        }
        return roomExist;
    }

    /**
     * Print the map
     */
    public void printMap() {
        // mincol/row inclusize, maxcol/row exclusive
        int mincol = 0;
        int maxcol = width;
        int minrow = 0;
        int maxrow = height;
        for (int iter = 0; iter < width; iter++) {
            if (colExistRoom(iter)) {
                break;
            }
            mincol++;
        }
        for (int iter = 0; iter < height; iter++) {
            if (rowExistRoom(iter)) {
                break;
            }
            minrow++;
        }
        for (int iter = width - 1; iter >= 0; iter--) {
            if (colExistRoom(iter)) {
                break;
            }
            maxcol--;
        }
        for (int iter = height - 1; iter >= 0; iter--) {
            if (rowExistRoom(iter)) {
                break;
            }
            maxrow--;
        }
        for (int iter = minrow; iter < maxrow; iter++) {
            for (int iter2 = mincol; iter2 < maxcol; iter2++) {
                if (rooms[(iter * width) + iter2].isEmpty()
                        || !rooms[(iter * width) + iter2].isPresent()) {
                    rooms[(iter * width) + iter2].printEmptyLine();
                    System.out.printf("%-2s", "");
                } else {
                    System.out.printf("%-1s", "");
                    rooms[(iter * width) + iter2].printTop();
                    System.out.printf("%-1s", "");
                }
            }
            System.out.print("\n");
            for (int iter2 = mincol; iter2 < maxcol; iter2++) {
                if (rooms[(iter * width) + iter2].isEmpty()
                        || !rooms[(iter * width) + iter2].isPresent()) {
                    rooms[(iter * width) + iter2].printEmptyLine();
                    System.out.printf("%-2s", "");
                } else {
                    rooms[(iter * width) + iter2].printSecondLine();
                }
                // System.out.printf("%-3s", "");
            }
            System.out.print("\n");
            for (int iter2 = mincol; iter2 < maxcol; iter2++) {
                if (rooms[(iter * width) + iter2].isEmpty()
                        || !rooms[(iter * width) + iter2].isPresent()) {
                    rooms[(iter * width) + iter2].printEmptyLine();
                    System.out.printf("%-2s", "");
                } else {
                    rooms[(iter * width) + iter2].printThirdLine();
                }
                // System.out.printf("%-3s", "");
            }
            System.out.print("\n");
            for (int iter2 = mincol; iter2 < maxcol; iter2++) {
                if (rooms[(iter * width) + iter2].isEmpty()
                        || !rooms[(iter * width) + iter2].isPresent()) {
                    rooms[(iter * width) + iter2].printEmptyLine();
                    System.out.printf("%-2s", "");
                } else {
                    if ((iter * width) + iter2 == playerpos) {
                        rooms[(iter * width) + iter2].printFourthLine(true);
                    } else {
                        rooms[(iter * width) + iter2].printFourthLine(false);
                    }
                }
                // System.out.printf("%-3s", "");
            }
            System.out.print("\n");
            for (int iter2 = mincol; iter2 < maxcol; iter2++) {
                if (rooms[(iter * width) + iter2].isEmpty()
                        || !rooms[(iter * width) + iter2].isPresent()) {
                    rooms[(iter * width) + iter2].printEmptyLine();
                    System.out.printf("%-2s", "");
                } else {
                    System.out.printf("%-1s", "");
                    rooms[(iter * width) + iter2].printBottom();
                    System.out.printf("%-1s", "");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * move to a certain direction
     * 
     * @param direction
     */
    public void move(String direction) {
        prevpos = playerpos;
        String dir = direction.toLowerCase();
        switch (dir) {
            case "north":
                playerpos = playerpos - width;
                break;
            case "south":
                playerpos = playerpos + width;
                break;
            case "west":
                playerpos = playerpos - 1;
                break;
            case "east":
                playerpos = playerpos + 1;
        }
    }

    /**
     * Get the current room
     * 
     * @return current room
     */
    public Room getCurrentRoom() {
        return rooms[playerpos];
    }

    /**
     * go back to previous room
     */
    public void returnToPreviousPos() {
        this.playerpos = prevpos;
    }

}
