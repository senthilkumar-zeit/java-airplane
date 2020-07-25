package com.aero.seating;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

//@Slf4j
public class Main {


    int TOTAL_SEAT_COUNT = 18;
    final static String SEAT_EMPTY = "O", SEAT_FILLED = "X";
    //PrintStream log = System.out; // Print the log in server side

    public static void main(String[] args) {

        // Console - for input
        Scanner console = new Scanner(System.in);
        Main main = new Main();

        // Data - Define & Declare
        // A block seating arrangement
        int[][] A_Block_size = new int[2][3];
        TreeMap<String, String> A_Block = new TreeMap<>();
        A_Block.put("1,1", SEAT_EMPTY);
        A_Block.put("1,2", SEAT_EMPTY);
        A_Block.put("1,3", SEAT_EMPTY);
        A_Block.put("2,1", SEAT_EMPTY);
        A_Block.put("2,2", SEAT_EMPTY);
        A_Block.put("2,3", SEAT_EMPTY);

        TreeMap<String, String> A_Block_Aisle = new TreeMap<>(); // A - Aisle seating arrangement
        A_Block_Aisle.put("1,3", SEAT_EMPTY);
        A_Block_Aisle.put("2,3", SEAT_EMPTY);

        TreeMap<String, String> A_Block_Window = new TreeMap<>(); // A - Window seating arrangement
        A_Block_Window.put("1,1", SEAT_EMPTY);
        A_Block_Window.put("2,1", SEAT_EMPTY);

        TreeMap<String, String> A_Block_Center = new TreeMap<>(); // A - Center seating arrangement
        A_Block_Center.put("1,2", SEAT_EMPTY);
        A_Block_Center.put("2,2", SEAT_EMPTY);

        // B block seating arrangement
        int[][] B_Block_size = new int[3][4];
        TreeMap<String, String> B_Block = new TreeMap<>();
        B_Block.put("1,1", SEAT_EMPTY);
        B_Block.put("1,2", SEAT_EMPTY);
        B_Block.put("1,3", SEAT_EMPTY);
        B_Block.put("1,4", SEAT_EMPTY);
        B_Block.put("2,1", SEAT_EMPTY);
        B_Block.put("2,2", SEAT_EMPTY);
        B_Block.put("2,3", SEAT_EMPTY);
        B_Block.put("2,4", SEAT_EMPTY);
        B_Block.put("3,1", SEAT_EMPTY);
        B_Block.put("3,2", SEAT_EMPTY);
        B_Block.put("3,3", SEAT_EMPTY);
        B_Block.put("3,4", SEAT_EMPTY);

        TreeMap<String, String> B_Block_Aisle = new TreeMap<>(); // B - Aisle seating arrangement
        B_Block_Aisle.put("1,1", SEAT_EMPTY);
        B_Block_Aisle.put("1,4", SEAT_EMPTY);
        B_Block_Aisle.put("2,1", SEAT_EMPTY);
        B_Block_Aisle.put("2,4", SEAT_EMPTY);
        B_Block_Aisle.put("3,1", SEAT_EMPTY);
        B_Block_Aisle.put("3,4", SEAT_EMPTY);

        TreeMap<String, String> B_Block_Window = null; // B - Window seating arrangement. No window seats

        TreeMap<String, String> B_Block_Center = new TreeMap<>(); // B - Center seating arrangement
        B_Block_Center.put("1,2", SEAT_EMPTY);
        B_Block_Center.put("1,3", SEAT_EMPTY);
        B_Block_Center.put("2,2", SEAT_EMPTY);
        B_Block_Center.put("2,3", SEAT_EMPTY);
        B_Block_Center.put("3,2", SEAT_EMPTY);
        B_Block_Center.put("3,3", SEAT_EMPTY);

        // Prompt for type of OPTIONS to fill seating
        System.out.println("Options to fill seat. \nOption 1 - Fill by specific seat. Option 2 - Fill by count \nEnter option (1 or 2):");
        String input_Option = console.nextLine();
        boolean printArrangement = false; // To print final output

        try {
            int option = Integer.valueOf(input_Option);

            // Option 1 - Fill by specific seat
            if (option == 1) {

                // Prompt for seat input
                System.out.println("Enter specific seat to assign (Block/rows,columns - Ex: A/1,3) :");
                String input_bySeat = console.nextLine();

                boolean isSeatFilled =
                        main.fillBy_SpecificSeat_Option1(input_bySeat, A_Block, B_Block);

                if (isSeatFilled)
                    printArrangement = true; // To print output
                else
                    System.out.println("Please enter valid seat number");

            }
            // Option 2 - Fill by count
            else if (option == 2) {

                // Prompt for seating input by number
                System.out.println("Enter number of seat to assign (Ex: 5) :");
                String input_byNumber = console.nextLine();

                // Fill the seating by given count
                boolean isSeatFilled =
                        main.fillBy_Count_Option2(input_byNumber,
                                A_Block, B_Block,
                                A_Block_Aisle, B_Block_Aisle,
                                A_Block_Window, B_Block_Window,
                                A_Block_Center, B_Block_Center);

                if (isSeatFilled)
                    printArrangement = true; // To print output

            } else {
                System.out.println("Please choose correct option. Option 1 or 2");
            }

        } catch (Exception optionException) {
            //main.log.println("Error - In option selection. Exception: " + optionException);
        }

        // Print seating arrangement
        if (printArrangement) {
            System.out.println("Seating Arrangement \n");
            main.printSeatingArrangement("A", A_Block_size, A_Block);
            main.printSeatingArrangement("B", B_Block_size, B_Block);
        }
    }


    /**
     * Fill the seating by specified seat number
     *
     * @param input_bySeat
     * @param A_Block
     * @param B_Block
     * @return
     */
    public boolean fillBy_SpecificSeat_Option1(String input_bySeat,
                                               TreeMap<String, String> A_Block,
                                               TreeMap<String, String> B_Block) {
        boolean isSeatFilled = false;

        // Input validation
        if (input_bySeat == null || A_Block == null || B_Block == null) {
            //log.println("Invalid - Input");
        }

            try {
                boolean invalidOption = false;
                String[] split = input_bySeat.split("/");
                String seat_Block = split[0], seat_key = split[1];

                if (split.length == 2) {
                    // Fill seating
                    if (seat_Block.equalsIgnoreCase("A")) {
                        A_Block.put(seat_key, SEAT_FILLED);
                        isSeatFilled = true;
                    } else if (seat_Block.equalsIgnoreCase("B")) {
                        B_Block.put(seat_key, SEAT_FILLED);
                        isSeatFilled = true;
                    } else
                        invalidOption = true;
                } else invalidOption = true;

                if (invalidOption)
                    System.out.println("Please enter valid Blocks A or B");
            } catch (Exception optionException) {
                //main.log.println("Error - In option selection. Exception: " + optionException);
            }

        return isSeatFilled;
    }

    /**
     * Fill the seating by given number of counts
     *
     * @param input_byNumber Number of seats to fill. User input.
     * @param A_Block
     * @param B_Block
     * @param A_Block_Aisle
     * @param B_Block_Aisle
     * @param A_Block_Window
     * @param B_Block_Window
     * @param A_Block_Center
     * @param B_Block_Center
     * @return
     */
    public boolean fillBy_Count_Option2(String input_byNumber,
                                        TreeMap<String, String> A_Block,
                                        TreeMap<String, String> B_Block,
                                        TreeMap<String, String> A_Block_Aisle,
                                        TreeMap<String, String> B_Block_Aisle,
                                        TreeMap<String, String> A_Block_Window,
                                        TreeMap<String, String> B_Block_Window,
                                        TreeMap<String, String> A_Block_Center,
                                        TreeMap<String, String> B_Block_Center) {
        boolean isSeatFilled = false;

        Main main = new Main();

        try {

            // Priority 1 - A Block - Aisle filling
            ArrayList<TreeMap<String, String>> block_Priority = new ArrayList<>();
            block_Priority.add(A_Block_Aisle);
            block_Priority.add(null);
            block_Priority.add(null); // Load Priority block

            int seatingCount_NonFilled = main.fillSeating(input_byNumber, A_Block, block_Priority);

            // Priority 1 - B Block - Aisle filling
            if (seatingCount_NonFilled > 0) {
                block_Priority = new ArrayList<>();
                block_Priority.add(B_Block_Aisle);
                block_Priority.add(null);block_Priority.add(null); // Load Priority block
                seatingCount_NonFilled = main.fillSeating(String.valueOf(seatingCount_NonFilled), B_Block, block_Priority);
            }

            // Priority 2 - A Block - Window filling
            if (seatingCount_NonFilled > 0) {
                block_Priority = new ArrayList<>();
                block_Priority.add(null);block_Priority.add(A_Block_Window);block_Priority.add(null); // Load Priority block
                seatingCount_NonFilled = main.fillSeating(String.valueOf(seatingCount_NonFilled), A_Block, block_Priority);
            }

            // Priority 2 - B Block - Window filling
            if (seatingCount_NonFilled > 0) {
                block_Priority = new ArrayList<>();
                block_Priority.add(null);block_Priority.add(B_Block_Window);block_Priority.add(null); // Load Priority block
                seatingCount_NonFilled = main.fillSeating(String.valueOf(seatingCount_NonFilled), B_Block, block_Priority);
            }

            // Priority 3 - A Block - Center filling
            if (seatingCount_NonFilled > 0) {
                block_Priority = new ArrayList<>();
                block_Priority.add(null);block_Priority.add(null);block_Priority.add(A_Block_Center); // Load Priority block
                seatingCount_NonFilled = main.fillSeating(String.valueOf(seatingCount_NonFilled), A_Block, block_Priority);
            }

            // Priority 3 - B Block - Center filling
            if (seatingCount_NonFilled > 0) {
                block_Priority = new ArrayList<>();
                block_Priority.add(null);block_Priority.add(null);block_Priority.add(B_Block_Center); // Load Priority block
                seatingCount_NonFilled = main.fillSeating(String.valueOf(seatingCount_NonFilled), B_Block, block_Priority);
            }

            if (seatingCount_NonFilled == 0)
                isSeatFilled = true;

            //log.println("Seat filling status: "+isSeatFilled);
        } catch (Exception exception) {
            //log.println("Error - Fill seating by count. Exception: " + exception);
        }
        return isSeatFilled;
    }

    /**
     * Fill the seating for given blocks
     *
     * @param input_byNumber
     * @param Main_Block
     * @param block_Priority
     * @return
     */
    public int fillSeating(String input_byNumber,
                           TreeMap<String, String> Main_Block,
                           ArrayList<TreeMap<String, String>> block_Priority) {

        int seatingCount_NonFilled = -1;

        // Input validation
        if (input_byNumber == null || Main_Block == null) {
            //log.println("Invalid - Input");
            return seatingCount_NonFilled; // False;
        }

        try {

            //log.println("Block seat filling - Start");

            // Number of seat to fill - User input
            int seatingCount = Integer.valueOf(input_byNumber);

            // Check total available seat
            if (seatingCount > TOTAL_SEAT_COUNT)
                System.out.println("Exceeds total number of available seats");

            seatingCount_NonFilled = seatingCount;

            TreeMap<String, String> Block_Aisle = block_Priority.get(0);
            TreeMap<String, String> Block_Window = block_Priority.get(1);
            TreeMap<String, String> Block_Center = block_Priority.get(2);

            // First fill - Aisle seat
            if (Block_Aisle != null) {
                breakBlock:
                {
                    for (int i = 0; i < seatingCount; i++) {

                        Iterator<String> keys_aisle = Block_Aisle.keySet().iterator();
                        while (keys_aisle.hasNext()) {

                            String key = keys_aisle.next();
                            if (Block_Aisle.get(key).equals(SEAT_EMPTY)) { // Check is seat already filled
                                Block_Aisle.put(key, SEAT_FILLED);
                                Main_Block.put(key, SEAT_FILLED); // Fill seat in A-Block
                                seatingCount_NonFilled--; // Reduce the non filled seat count

                                if (seatingCount_NonFilled == 0) break breakBlock; // Break this block
                            }
                        }
                    }
                }
                //log.println("Aisle seat filled");
            }

            // Second fill - Window seat
            if (Block_Window != null) {
                if (seatingCount_NonFilled > 0) {
                    breakBlock:
                    {
                        for (int i = 0; i < seatingCount_NonFilled; i++) {
                            Iterator<String> keys_window = Block_Window.keySet().iterator();
                            while (keys_window.hasNext()) {

                                String key = keys_window.next();
                                if (Block_Window.get(key).equals(SEAT_EMPTY)) { // Check is seat already filled
                                    Block_Window.put(key, SEAT_FILLED);
                                    Main_Block.put(key, SEAT_FILLED); // Fill seat in A-Block
                                    seatingCount_NonFilled--; // Reduce the non filled seat count

                                    if (seatingCount_NonFilled == 0) break breakBlock; // Break this block
                                }
                            }
                        }
                    }
                    //log.println("Window seat filled");
                }
            }

            // Third fill - Center seat
            if (Block_Center != null) {
                if (seatingCount_NonFilled > 0) {

                    breakBlock:
                    {
                        for (int i = 0; i < seatingCount_NonFilled; i++) {
                            Iterator<String> keys_center = Block_Center.keySet().iterator();
                            while (keys_center.hasNext()) {

                                String key = keys_center.next();
                                if (Block_Center.get(key).equals(SEAT_EMPTY)) { // Check is seat already filled
                                    Block_Center.put(key, SEAT_FILLED);
                                    Main_Block.put(key, SEAT_FILLED); // Fill seat in A-Block
                                    seatingCount_NonFilled--; // Reduce the non filled seat count

                                    if (seatingCount_NonFilled == 0) break breakBlock; // Break this block
                                }
                            }
                        }
                    }
                    //log.println("Center seat filled");
                }
            }

            // Final log
            //log.println("Block seat filling - Done. seatingCount_NonFilled: " + seatingCount_NonFilled);

        } catch (Exception exception) {
            //log.println("Error - Seating by numbers. Exception: " + exception);
        }

        return seatingCount_NonFilled;
    }

    /**
     * Print seating arrangement. Final output to console
     *
     * @param whichBlock A/B block
     * @param Block_size
     * @param Block
     */
    public void printSeatingArrangement(String whichBlock, int[][] Block_size, TreeMap<String, String> Block) {

        System.out.println("Block - " + whichBlock);
        System.out.println("\n- - - - -");

        try {

            // Loop by size of block
            for (int i = 0; i < Block_size.length; i++) {
                System.out.println("\n");
                for (int j = 0; j < Block_size[i].length; j++) {

                    // Fetch from 'Block'
                    System.out.print(Block.get(String.valueOf((i + 1) + "," + (j + 1))) + " ");
                }
            }
            System.out.println("\n");
        } catch (Exception exception) {
            //log.println("Error - Printing seating arrangement. Exception: " + exception);
        }
    }
}
