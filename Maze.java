import java.io.*;
import java.util.Scanner;

/**
* The Maze program implements an application that
* solves mazes in .txt format.
*
* @author  Marcus A. Mosley
* @version 1.0
* @since   2021-05-27
*/
public class Maze {
  /**
  * The Main method receives input and checks viability.
  */
  public static void main(String[] args)throws Exception {
    Scanner input = new Scanner(System.in);
    System.out.println("\nWhat is the Maze file? ");
    String fileName = input.nextLine();
    
    File file = new File(fileName);
    
    int lines = 0;
    BufferedReader line = new BufferedReader(new FileReader(file));
    
    while (line.readLine() != null) {
      lines++;
    }
    
    String str;
    String string = "";
    int count = 0;
    BufferedReader text = new BufferedReader(new FileReader(file));
    
    while ((str = text.readLine()) != null) {
      string += str;
      count++;
    }

    char[] maze = string.toCharArray();
    int length = maze.length / lines;
    
    findStart(maze, length);
    done(maze, length);
  }
  
  /**
  * The findStart method find the start of the maze.
  */
  public static void findStart(char[] maze, int length) {
    int start = -1;
    for (int counter = 0; counter < maze.length; counter++) {
      if (maze[counter] == 'S') {
        start = counter;
      }
    }
    
    if (start != -1) {
      solve(maze, length, start);
    } else {
      System.out.print("Error");
    }
  }
  
  /**
  * The solve method solves the maze.
  */
  public static Boolean solve(char[] maze, int length, int start) {
    if (maze[start] == 'G') {
      return true;
    }
    
    if (0 != (start + 1) % length) {
      if (maze[start + 1] == '.') {
        char tmp = maze[start];
        maze[start] = '+';
        start++;
        if (!solve(maze, length, start)) {
          start--;
          maze[start] = tmp;
        } else {
          return true;
        }
      } else if (maze[start + 1] == 'G') {
        maze[start] = '+';
        start++;
        return true;
      }
    }
    
    if (1 != (start + 1) % length) {
      if (maze[start - 1] == '.') {
        char tmp = maze[start];
        maze[start] = '+';
        start--;
        if (!solve(maze, length, start)) {
          start++;
          maze[start] = tmp;
        } else {
          return true;
        }
      } else if (maze[start - 1] == 'G') {
        maze[start] = '+';
        start--;
        return true;
      }
    }
    
    if (0 < start - length) {
      if (maze[start - length] == '.') {
        char tmp = maze[start];
        maze[start] = '+';
        start -= length;
        if (!solve(maze, length, start)) {
          start += length;
          maze[start] = tmp;
        } else {
          return true;
        }
      } else if (maze[start - length] == 'G') {
        maze[start] = '+';
        start -= length;
        return true;
      }
    }
    
    if (maze.length > (start + length)) {
      if (maze[start + length] == '.') {
        char tmp = maze[start];
        maze[start] = '+';
        start += length;
        if (!solve(maze, length, start)) {
          start -= length;
          maze[start] = tmp;
        } else {
          return true;
        }
      } else if (maze[start + length] == 'G') {
        maze[start] = '+';
        start += length;
        return true;
      }
    }
    return false;
  }
    
  /**
  * The done method shows the completed maze.
  */
  public static void done(char[] maze, int length) {
    System.out.print("\nSolved\n");
    for (int count = 0; count < maze.length; count++) {
      if (0 == (count + 1) % length) {
        System.out.print(maze[count] + "\n");
      } else {
        System.out.print(maze[count]);
      }
    }
  }
}