# CS 102, Project 3
The decoding key is a string with characters `F`, `B`, `R`. Each of those characters is an instruction to be applied to the numerical sequence in order to decode it.

*   `F` drop/remove the front/first element in the sequence,
*   `B` drop/remove the back/last element in the sequence,
*   `R` reverse the sequence

The first two instructions (`F` and `B`) cannot be applied to an empty sequence. If such an attempt is made, the decoding algorithm should detect it and produce appropriate error message.

The reverse instruction (`R`) can be applied to an empty sequence.

The `Decode.java` file provides function stubs along with the documentation for three functions:

*   `isValid` that determines if a decoding key is a valid key
*   `parseSequence` that converts a string containing the numerical sequence to an `MDeque` object (see below for details about `MDeque` class)
*   `decode` that decodes the given sequence following the decoding key

Your task is to complete the implementation of `isValid` and `decode` functions.

Source code: [Decode.java](https://cs.nyu.edu/~joannakl/cs102_f22/projects/project3/Decode.java)

IMPLEMENTATION RESTRICTION: the `isValid` function should be implemented recursively.

#### Example of Decoding 1

```
sequence: 12, 6, 9, 20, 17, -8, 19, 22
decoding key: FBRFBR

```


```
instruction applied            resulting sequence
__________________________________________________
    F                       6, 9, 20, 17, -8, 19, 22
    B                       6, 9, 20, 17, -8, 19
    R                       19, -8, 17, 20, 9, 6
    F                       -8, 17, 20, 9, 6
    B                       -8, 17, 20, 9
    R                       9, 20, 17, -8

```


#### Example of Decoding 2

```
sequence: 12, 6, 9, 20, 17, -8
decoding key: FFBBRRFFBBRR

```


```
instruction applied            resulting sequence
__________________________________________________
    F                       6, 9, 20, 17, -8
    F                       9, 20, 17, -8
    B                       9, 20, 17
    B                       9, 20
    R                       20, 9
    R                       9, 20
    F                       20
    F
    B                       error: cannot drop from an empty sequence
    B
    R
    R

```
