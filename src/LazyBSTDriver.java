// Kyle Custodio | kyc180000
// Data Structures and Introduction to Algorithmic Analysis | CS 3345
// Section: 001
// Semester: Fall 2019
// Project 3: Implements a BST with lazy deletion

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class LazyBSTDriver {
    public static void main(String[] args) {
        Scanner in;
        if (args.length != 2) {
            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
            System.exit(0);
        }
        try {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
            File outputFile = new File(args[1]);
            PrintWriter out;
            out = new PrintWriter(outputFile);

            LazyBinarySearchTree tree = new LazyBinarySearchTree();
            String operation = "";

            while (in.hasNextLine()) {
                operation = in.nextLine();
                if (operation.startsWith("Insert:")) {
                    // insert
                    try {
                        int num = Integer.parseInt(operation.substring(7));
                        out.println(tree.insert(num));
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                } else if (operation.startsWith("Delete:")) {
                    // delete
                    try {
                        int num = Integer.parseInt(operation.substring(7));
                        out.println(tree.delete(num));
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                } else if (operation.startsWith("Contains:")) {
                    // contains
                    try {
                        int num = Integer.parseInt(operation.substring(9));
                        out.println(tree.contains(num));
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                } else if (operation.equals("PrintTree")) {
                    // print tree
                    out.println(tree.toString());
                } else if (operation.equals("FindMin")) {
                    // find min
                    out.println(tree.findMin());
                } else if (operation.equals("FindMax")) {
                    // find max
                    out.println(tree.findMax());
                } else if (operation.equals("Height")) {
                    // height
                    out.println(tree.height());
                } else if (operation.equals("Size")) {
                    // size
                    out.println(tree.size());
                } else {
                    // error
                    out.println("Error in line: " + operation);
                }
            }
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
