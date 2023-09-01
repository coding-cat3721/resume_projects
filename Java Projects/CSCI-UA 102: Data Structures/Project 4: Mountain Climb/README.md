### Introduction and objectives

The goal of this project is to implement a program that uses a binary search tree to model a mountain hiking expedition: A hiker starts at the top of the mountain. They need to find their way down the mountain with limited resources to survive and overcome obstacles. Your program will produce a list of all the possible paths that the hiker should take to survive.

The mountain is represented by a binary search tree. Nodes in this tree represent a rest-stops. The hiker restocks their supplies at those rest-stops. But they may also encounter obstacles at the rest-stops. Some of the supplies that they carry with them may help them pass through the obstacles. The paths from the root down represent possible trails. One the hiker starts a single path, they do not have an option of going back. Some of the paths lead to cliffs, others leads to the bottom of the mountain. See example below for more details.

**Start early!** This project may not seem like much coding, but debugging and testing always takes time, especially for recursive algorithms.

* * *

  

The credit for this project idea goes to Karl R. Rosenberg and Abhinav Gupta.  

 User Interface

Your program has to be a console based program (no graphical interface). The user should not be prompted for any information (all required information for the program to run is provided in the input file given as a command line argument). The output of the program should be printed to standard output stream. Any error messages should be printed to the standard error stream.

 Program Usage

The program is started from the command line (or run within an IDE). It expects one command line argument: the name of the input file.  

The input file will be a test file. Each line in the input describes a single point on the mountain (node in a BST). The format of each line is as follows:

`LABEL SUPPLIES OBSTACLES`

In the above, `LABEL` is a string with the name of the rest stop. The alphanumeric comparison of the labels determines the shape of the tree. `SUPPLIES` is a (possibly empty) list of supplies that the hiker will get at this rest-stop. Valid supplies are `food`, `raft`, `axe`. `OBSTACLES` is a (possibly empty) list of obstacles that the hiker will encounter at this rest stop. Valid obstacles are `fallen tree` and `river`.

See below for an example.

If the program is executed with non-existent or invalid command line argument, it should print an error message and terminate.

If the file contains any additional (and invalid) strings in the description of a rest-stop, they should be ignored.

The program \\textbf{should not be interactive}. All input should be provided as the command line arguments. The user should not be prompted for any additional information.

 Results/Output

The program has to calculate all possible paths down the mountain that lead all the way down to the lowes level (i.e., to the leaves in the lowest level of the mountain-tree) that the hiker can take and survive.

The program output should consist of number of lines equal to the number of safe paths. Each safe path should consist of a space separated list of the rest-stop labels in order in which they are followed from top of the mountain to the bottom. The paths should be printed in order from _left_ to _right_ in the tree that represents the mountain (this is also an alphanumeric ordering of the paths).

 Example

![image](https://github.com/coding-cat3721/resume_projects/assets/85908297/a49c423e-4e6b-4b6d-81f5-f2980b2932d3)

The above figure depicts visually a tree that results from the following input file

    J food raft food
    E fallen tree
    G food axe river
    I food fallen tree
    H
    F
    B food
    A
    D axe food
    C
    P
    L food food river
    R axe food raft
    Q
    K
    N axe
    M
    O
    

`food` is a single ration that lasts hiker the time needed to traverse a single segment of the trail (a single edge in the tree)

`axe` is needed when the trail is obstructed by a `fallen tree`. The hiker cannot pass a `fallen tree` obstacle unless they have an `axe`. Once they use the `axe`, it becomes dull and cannot be used again.

`raft` is needed when the trail continues through a river. The hiker cannot pass a `river` obstacle unless they have a `raft`. Each `raft` is designed for single river crossing - once used, it cannot be used again.

Note that the hiker is able to use an axe or a raft at the very same rest stop at which it was collected or at any rest stop later along the journey.

There are many possible paths down the mountain depicted above, but only two lead to safety.

*   `J E B A`, `J E B D C`, `J E G F`, `J E G I H` - the hiker reaches rest-stop `E` and cannot continue without an `axe`.
*   `J P L K` - the hiker reaches rest-stop K and falls off the cliff.
*   `J P L N M`, `J P L N O` - the hiker uses the `raft` at rest-stop `L` and with new food supplies continues to the bottom of the mountain at either `M` or `O`.
*   `J P R Q` - the hiker reaches rest-stop Q and falls off the cliff.

In this case, the program should produce two lines of output:

    J P L N M
    J P L N 0
    

 Data Storage and Organization

The design of classes is up to you, but you do need to implement certain classes to represent certain entities in the program. You need to make decisions about how to design these classes to produce an efficient and well-put-together program. Make sure that all methods that you include in a particular class belong in that class.

#### `MountainClimb` class

This is the class that is the program. This means it has the `main` method. This class is responsible for parsing and validating the command line arguments, reading and parsing the input file, producing any error messages, handling any exceptions thrown by other classes, and producing output.

#### `BST<E>` class

This is a generic `BST<E>` class. The specification is similar to the one for `TreeSet` class provided by Java libraries, but your implementation will be very different.

The specification for this class is provided at [its javadoc page](project4/project4/BST.html). You can use the source code that we wrote in class, but keep in mind that the class that you are implementing requires additional functionality and you may need to rewrite some of the methods that were created in class.

**`Node` class**

The program should provide and use a nested class (to learn more about nested and inner classes see: https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html) that provides nodes for your tree. The details of the implementation of that class are up to you, but this class should be private (or protected, if another class in your code inherits from it and needs to be able to create variables of type `Node`):

    private class Node
    

HINT: to improve the performance of your BST algorithms, it may be useful to keep additional data fields in the nodes, i.e., more than just data, left and right. Those design decisions are up to you. But you should explain in comments for this class, why you have additional data fields if you chose to do so.

**Iterator**

The `BST<E>` class implements `Iterable<E>` interface. This means that its `iterator()` method needs to return an instance of a class that implements the `Iterator<E>` interface. The `iterator()` method should return an iterator instance that accesses the values in the tree according to the inorder traversal of the binary search tree. The two additional methods `preorderIterator()` and `postOrederIterator()` need to return iterators that access the values in the tree according to the preorder and postorder traversals, respectively.

The details of the implementation are up to you and you are free to implement more than one internal private iterator class. The `next()` and `hasNext()` methods of the iterator classes should perform in O(1). The constructor of the iterator classes should be O(N).

The remove method in the `Iterator<E>` interface is optional and you do not need to provide the actual remove functionality. (This means that the method has to exist, but instead of performing its function, it throws an instance of `UnsopportedOperationException`.)

  

NOTE: normally, all data fields in the class should be private. But since the `BST` class serves as a base class for the `BSTMountain` class below, its data fields can be made protected instead to allow the subclass to access these data fields.

#### `BSTMountain` class

This should be the class that inherits from your own `BST<RestStop>`. Your class should represent the mountain itself (therefore, it should not be generic and its nodes should store data items of type `RestStop`). It is up to you to decide how to implement this class, which methods to provide etc.

#### `RestStop` class

This class should represent a single rest stop. It should be capable of storing the label of the rest stop along with a list of the supplies that a hiker can collect at this rest-stop and a list of obstacles that a hiker may encounter at this rest-stop. It may be useful to implement the `Comparable` interface.

#### `Hiker` class

This class should represent a hiker traveling down the mountain. An object of this class should be capable of keeping track of all the supplies that the hiker has in their possession. This information should be updated as the hiker travels along a trail and consumes supplies (by either eating along the way, or using the tools to clear the path, or cross the river).

You may, but you are not required to, implement other classes.

 Programming Rules

*   You should follow the rules outlined in the document [Code conventions](https://cs.nyu.edu/~joannakl/cs102_s21/notes/CodeConventions.pdf) posted on the course website.
    
*   You have to work with your own implementation of a BST class. It should be based on the one we have been using in class, but it cannot be based on one of the implementations in Java libraries.
    
*   You may use any exception-related classes.
    

 Working on This Assignment

*   **You should start right away!**
*   You should modularize your design so that you can test it regularly: for example, implement the part of the code that reads and parses the input file, then implement and test individual classes, then implement the part that provides the interactive part of the program, … .
*   Make sure that at all times you **have a working program!** You should also implement stubs of methods that return `0` or `null`. This way your code compiles, even though it may not work completely. You can implement methods that perform one task at a time. This way, if you run out of time, at least parts of your program will be functioning properly.
*   You should make sure that you are **testing the program on much smaller data set** for which you can determine the correct output manually. You can create a test input file that contains only a few rows.
*   You should make sure that your program's results are consistent with what is described in this specification by running the program on carefully designed test inputs and examining the outputs produced to make sure they are correct. The goal in doing this is to try to find the mistakes you have most likely made in your code.  
    **DO NOT** test your program on the entire large input file. This may take a long time and you will never know if the results are correct or not.
*   Each class that you submit **will be tested by itself without the context of other classes that you are implementing for this assignment**.  
    This means that you need to make sure that your methods can perform their tasks correctly even if they are executed in situations that would not arise in the context of this specific program.
*   You should **backup** your code after each time you spend some time working on it. Save it to a flash drive, email it to yourself, upload it to your Google drive, push it to a private git repository, do anything that gives you a second (or maybe third copy). Computers tend to break just a few days or even a few hours before the due dates - make sure that you have working code if that happens.

 Grading

If your program does not compile or if it crashes (almost) every time it is run, you will get a zero on the assignment. Make sure that you are submitting functioning and documented code, even if it is not a complete implementation so that you can get partial credit.

If the program does not adhere to the specification, the grade will be low and will depend on how easy it is to figure out what the program is doing and how to work with it.

The grade will be determined by several factors:

*   50 points: class correctness: correct behavior of the program in many different input files
*   20 points: design and the implementation of your code
*   10 points: efficient implementation (this will be determined by a code review)
*   20 points: proper documentation, program style and format of submission (this will be determined by a code review)

 How and What to Submit

**For the purpose of grading, your project must be be in the package called `project4`. This means that each of your submitted source code files should start with a line:**

`package project4;`

Your should submit all your source code files (the ones with `.java` extensions only) either in a single **zip** file or as individual files to Gradescope. **DO NOT** submit `.class` files or any project files that your IDE might produce, or any other files created by your operating system and file system. You can see all the files on Gradescope. Verify that there is nothing there that you did not intend to put it.

You can produce a zip file directly from Eclipse (if this is what you are using):

*   right click on the name of the package (inside the `src` folder) and select Export…
*   under General pick Archive File and click Next
*   in the window that opens select appropriate files and settings:
    *   in the right pane pick ONLY the files that are actually part of the project, but make sure that you select all files that are needed
    *   in the left pane, make sure that no other directories are selected
    *   click Browse and navigate to a location that you can easily find on your system (Desktop or folder with the course materials or …)
    *   in Options select "Save in zip format", "Compress the contents of the file" and "Create only selected directories"
*   click Finish

You may resubmit to Gradescope as many times as you wish before the submission link closes. But if you resubmit after the grace period ends, your assignment will be subject to the late point deductions.

Some of the autograder tests for this project will be hidden. This means that you need to make the effort to test your own implementation before you make the final submission.

[Computer Science Department](http://www.nyu.edu/registrar/calendars/university-academic-calendar.html#1198>Fall 2018 Calendar</a> ·
            <a href=) · [New York University](http://www.nyu.edu/)

[![Creative Commons License](https://i.creativecommons.org/l/by/4.0/80x15.png)](http://creativecommons.org/licenses/by/4.0/)  
This work is licensed under a Creative Commons Attribution 4.0 International License.
