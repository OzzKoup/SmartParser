# Description :
 It is a HTML analyzer program which can find the most similar element in comparison with source one.

# To run this application : 
java -jar [xml-analyzer.jar] [sample-0-origin.html] [other html file]

# Result file :
#### This application writes the result in file.
#### The path of output file : $user.home/SmartAnalyzerResult.txt
#### For example : C:/Users/Username/SmartAnalyzerResult.txt

# Output :

## sample-1-evil-gemini :

    html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > a[1]

## sample-2-container-and-clone :

    html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > div[0] > a[0]

## sample-3-the-escape :

    html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[2] > a[0]

## sample-4-the-mash :

    html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[2] > a[0]
