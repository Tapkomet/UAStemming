# UAStemming
Stemming algrotihm for Ukrainian language. Implementations in Snowball and Java, although C can be generated as well.

stem_ukr.sbl contains the original implementation in Snowball. The approach prefers removing suffixes and compeltely ignores preffixes. Roughly 95% accuracy when tested.

testUkrainian.txt contains test data from a variety of Ukrainian Wikipedia articles. Results.txt contains unique results of stemming. The rest of the files include slightly modified source Java files from the Snowball repository located at https://github.com/snowballstem , a tester, the generated Java code, plus binaries and settings for an Eclipse project.

Java code can be regenerated, or C code generated, following the instructions at http://snowball.tartarus.org/runtime/use.html
The generated Ukrainian.java may require slight tweaking to work afterwards, such as inserting the class name wherever absent.
