## CS 102, Project 2
Your need to provide an implementation of several classes that store the data and compute the results when the program is executed.

In particular, your program must implement and use the following classes. You may implement additional classes and additional methods in the required classes, if you wish.

**As you are working on your classes, keep in mind that they should be (and will be) tested separately from the rest of your program.**

#### `TreeSpecies` class

This class should represent a single tree species that has both the common name and the Latin name of the species.

The class should provide a two parameter constructor

`public TreeSpecies ( String commonName, String latinName)`

Both names are, possibly empty, strings. They cannot be `null`. If this constructor is called with a `null` argument, it should throw and instance of `IllegalArgumentException` with an appropriate error message.

  
The class should override the `equals` method. Two `TreeSpecies` objects are equal if both the names are the same. The comparison should be case insensitive.

#### `Tree` class

The `Tree` class stores information about a particular tree that grows in New York City. The class should store only a subset of the entries from the input file, namely:

*   tree\_id as a non-negative integer
*   status as a string, valid values: "Alive", "Dead", "Stump", or empty string or `null`
*   health as a string, valid values: "Good", "Fair", "Poor", or empty string or `null`
*   spc\_latin (Latin name) as a, possibly empty, string, cannot be `null`
*   spc\_common (common name) as a, possibly empty, string, cannot be `null`
*   zipcode as a positive five digit integer (This means that any number from 0 to 99999 is acceptable. The values that are shorter should be treated as if they had leading zeroes, i.e., 8608 represents zipcode 08608, 98 represents zip code 00098, etc.)
*   boroname as a string, valid values: "Manhattan", "Bronx", "Brooklyn", "Queens", "Staten Island"
*   x\_sp as a double
*   y\_sp as a double

All of the string data fields should be case insensitive - i.e., "Alive", "alive", "ALIVE" and "aLIVe" are all valid values for the status data field.

Note that some of these data fields may not be needed in this project, but the code has to provide them and the data for them has to be stored.

This class should provide a two parameter constructor:

`public Tree (int treeID, TreeSpecies species )`

The `treeID` should be a non-negative integer and the `species` should not be `null`. If the constructor is called with invalid arguments, then an instance of `IllegalArgumentException` should be thrown carrying an appropriate error message.

There should be no default constructor. (A default constructor is one that can be used without passing any arguments.)

The class should provide getters and setters for all other required data fields. The parameters for all setters should be validated according to the rules specified above. If a setter is called with invalid arguments, then an instance of `IllegalArgumentException` should be thrown carrying an appropriate error message.

This class should override the `equals` methods (see the documentation for the `Object` class for details). The two `Tree` objects should be considered equal if their id's and both, Latin and common, species names are the same. The other values should not be considered in the equality. The name comparison should be case insensitive.

This class should implement `Comparable<Tree>` interface. The comparison should be done by the common species name as the primary key (using alphabetical order), and by the tree id as the secondary key (i.e., when two objects that have the same species name are compared, the comparison should be performed by the id). The comparison method should be case insensitive (i.e., two `Tree` objects with the species name stored as "Baldcypress" and "BaldCypress" should be compared by their id's since their names are the same).

The class should override the `toString` method. The details are up to you, but you should make sure that it returns a `String` object that is a meaningful representation of the object on which it is called.

#### `TreeSpeciesList` class

The `TreeSpeciesList` class should be used to store all the unique `TreeSpecies` objects. This class should inherit from the `ArrayList<TreeSpecies>` class. (Some of you may realize that a `HashTable` would probably be a better choice here. For the purpose of this project, you should NOT use the `HashTable` or any other hashing data structure).  

The class needs to provide a default constructor that creates an empty `TreeSpeciesList` object.

  
In addition, the class should implement the following methods:

*   `public TreeSpeciesList getByCommonName (String keyword)` returns a list of species whose common name contains `keyword` as a substring. The method should be case insensitive. It should throw and instance of `IllegalArgumentException` exception if called with `null` argument. It should return `null` if there are no `TreeSpecies` objects matching the `keyword`.
    
*   `public TreeSpeciesList getByLatinName (String keyword)` returns a list of species whose Latin name contains `keyword` as a substring. The method should be case insensitive. It should throw and instance of `IllegalArgumentException` exception if called with `null` argument. It should return `null` if there are no `TreeSpecies` objects matching the `keyword`.
    

#### `TreeList` class

The `TreeList` class should be used to store all the `Tree` objects.

This class should be a simple version of a linked list. By _simple_ we mean that you do not need to provide all the methods to make it a linked list, but the connectivity between elements should be done based on the linked list structure. (This is not a generic class. You will need to have an internal `Node` class that stores a `Tree` object as data and a reference to the next node. You will need to keep track of the `head` and, possibly `tail` references. )

The class needs to provide the default constructor that creates an empty list.

`public TreeList ( )`

The class should implement

*   `public void add(Tree tree)` method that adds a given `tree` object to the current list; this method should throw an instance of `IllegalArgumentException` if the specified reference is `null`
*   `public int getTotalNumberOfTrees()` method that returns the total number of `Tree` objects stored in this list.
*   `public int getCountByCommonName ( String speciesName )` method that returns the number of `Tree` objects in the list whose common name is the same as the given `speciesName`. This method should be case insensitive. If the method is called with a non-existent species, the return value should be 0.
*   `public int getCountByLatinName ( String speciesName )` method that returns the number of `Tree` objects in the list whose Latin name is the same as the given `speciesName`. This method should be case insensitive. If the method is called with a non-existent species, the return value should be 0.
*   `public int getCountByBorough ( String boroName )` method that returns the number of `Tree` objects in the list that are located in the borough equal to `boroName`. This method should be case insensitive. If the method is called with a non-existent borough name, the return value should be 0.
*   `public int getCountByCommonNameBorough ( String speciesName, String boroName )` method that returns the number of `Tree` objects in the list whose common name is the same as the given `speciesName` and which are located in the borough `boroName`. This method should be case insensitive. If the method is called with a non-existent borough name or species, the return value should be 0.
*   `public int getCountByLatinNameBorough ( String speciesName, String boroName )` method that returns the number of `Tree` objects in the list whose Latin name is the same as the given `speciesName` and which are located in the borough `boroName`. This method should be case insensitive. If the method is called with a non-existent borough name or species, the return value should be 0.

The class should override the `toString` method. The details are up to you, but you should make sure that it returns a `String` object that is a meaningful representation of the object on which it is called (it may or may not contain the listing of all of the elements).

#### `NYCStreetTrees` class

The `NYCStreetTrees` class is the actual program. This is the class that should contain the `main` method. It is responsible for opening and reading the data file, obtaining user input, performing some data validation and handling all errors that may occur, and printing all output to the standard output stream. It should handle any exceptions thrown by your other classes and terminate gracefully, if need be, with a friendly error message presented to the user: The program should never just reprint the exception message as a way of handling an exception. These messages are rarely readable to the ordinary user and make it seem like the program crashed in response to the exception.

You may (and probably should) implement other methods in this class to modularize the design.
