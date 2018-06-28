# Project
Image Editor

Image Editing is an intense process that involves various operations such as color alteration by using different color enhancement methods like Grey Scaling, Image Negative etc, and also by setting Red, Blue, Green to a absolute value or to a predefined value. It’s obvious that these are all a part of creative methods that can simply make your images outstanding, hence this are very advantageous for customers from different industries and sectors to share single environment of editing images. With these services customers can separate objects from their background by selection tool, correct colors and adjust tone in images, remove unwanted elements from your images by cropping, change the color of all the sections of an image.

Getting Started

Prerequisites

Java.

Installing Installing Java with apt-get is easy. First, update the package index:

$ sudo apt-get update Then, check if Java is not already installed:

java -version If it returns "The program java can be found in the following packages", Java hasn't been installed yet, so execute the following command:

$ sudo apt-get install default-jre This will install the Java Runtime Environment (JRE). If you instead need the Java Development Kit (JDK), which is usually needed to compile Java applications (for example Apache Ant, Apache Maven, Eclipse and IntelliJ IDEA execute the following command:

$ sudo apt-get install default-jdk

Built With

Ecllipse Java IDE

Methods

OpenFile Used to open the image for editing.We can browse the computer storage drive through a graphical user interface. The file chooser window will only show directories and the image files.The image is stored in the program as a BufferedImage object named as “oimage” and a copy of “Oimage” is stored as an BufferedImage Object named “Uimage” for undoing the changes.

Save It is used to save the edited image after the editing is completed.It saves the edited image with “.jpg “ extension. It saves the image with name as the original name concatenated with “OUTPUT” at the last.It saves the image in the same directory at that of the original image.

GreyScale It takes the image.It retrieves the values of red, green and blue component of each pixel.Then it replaces the red, green and blue value by their average for each pixel.

Negative It takes the image.It retrieves the values of red, green and blue component of each pixel. Then it replaces the red, green and blue by (255-red), (255-green) and (255-blue) respectively.

RedEffect It takes the image.It retrieves the values of red, green and blue component of each pixel. Then it replaces the green and blue value by their half for each pixel.

GreenEffect It takes the image.It retrieves the values of red, green and blue component of each pixel. Then it replaces the red and blue value by their half for each pixel.

BlueEffect It takes the image.It retrieves the values of red, green and blue component of each pixel. Then it replaces the red and green value by their half for each pixel.

Red It takes the image.It retrieves the values of red component of each pixel. Then it set the red value to an absolute value as set by the user through the red slider for each and every pixel of the image.

Green It takes the image.It retrieves the values of green component of each pixel. Then it set the green value to an absolute value as set by the user through the green slider for each and every pixel of the image.

Blue It takes the image.It retrieves the values of blue component of each pixel. Then it set the blue value to an absolute value as set by the user through the blue slider for each and every pixel of the image.

Crop This is used to crop the image.In order to crop the image ,the user
have to first click on the image to specify the top left vertex of the portion image to be cropped and then click on the bottom right of the portion of image to be cropped.

Undo It uses a stack to store any recent changes to the image.Whenever
we press the Undo button, it revert the last change made by the user.

Author

Aakash

Acknowledgments

Hat tip to anyone who's code was used Inspiration etc
