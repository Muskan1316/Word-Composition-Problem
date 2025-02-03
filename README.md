Longest Concatenated Words Finder

Overview

This Java program identifies the longest and second longest compound words from a given list. A compound word is a word that can be formed entirely by concatenating shorter words present in the same list. The program reads words from a file, processes them efficiently using a Trie data structure, and determines the required compound words while measuring execution time.

Approach

The problem is solved using a Trie-based approach to efficiently store and search words. The program first inserts all words into the Trie for quick prefix lookups. Then, it iterates through each word, using a recursive function prefixChecker() to determine whether it can be formed by concatenating smaller words. The longest and second-longest compound words are tracked during this process. Additionally, the program calculates the total execution time to provide performance insights.
