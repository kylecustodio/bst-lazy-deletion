Kyle Custodio | kyc180000
Project 3
    LazyBinarySearchTree.java
    LazyBSTDriver.java (Main Class)
IDE: JetBrains
JDK 11


Ex.
javac LazyBSTDriver.java
java LazyBSTDriver C:\Users\name\Documents\School\cs3345\Project3\testinput.txt C:\Users\name\Documents\School\cs3345\Project3\testoutput.txt

    testinput.txt
        Insert:98
        Insert:67
        Insert:55
        Insert:45
        PrintTree
        Delete:84
        Delete:45
        Contains:45
        FindMin
        FindMax
        PrintTree
        Height
        Size
        Insert:84
        Insert:32
        Insert:132
        PrintTree
        Insert:980
        Insert
        hih
  =>testoutput.txt
        true
        true
        true
        true
        98 67 55 45
        false
        true
        false
        55
        98
        98 67 55 *45
        3
        4
        true
        true
        Error in insert: IllegalArgumentException raised
        98 67 55 *45 32 84
        Error in insert: IllegalArgumentException raised
        Error in line: Insert
        Error in line: hih