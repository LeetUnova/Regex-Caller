# Regex Caller
This application created with java takes a text file with a list of regexes and replacement text, and uses them to output new files based on input files.
## Example
### Regex file
```
\w+
hello

(!)
$1$1
```
### Input file
```
Hello World!
```
### Output file
```
hello hello!!
```
You can use groups and other regex features, as long as they are supported in Java.
## Use
To use the caller, you can call ```java -jar regexcaller.jar [regexes] [destination] [files...]``` on the command line. ```[regexes]``` is the text file with the regexes, ```[destination]``` is the destination folder to put output files into, and ```[files...]``` is any number of text files to use as input.
