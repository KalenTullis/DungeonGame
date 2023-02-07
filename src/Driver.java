//Driver class for dungeon game
//Terminal based adventure for traversing 
//to the end of the dungeon with limited hit points

import java.util.Scanner;
import java.util.ArrayList;
class Driver { //Monsters spawn with 50-100 gold that player takes
    static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    /////////Player initializing the game//////////

    //Creating player object  
    Player player = makePlayer();
  
    //Creating dungeon
    int width = getDungeonSize();
    input.nextLine();
    
    //Creating Monsters
    ArrayList<Player> monsters = makeMonsters(width);

    ///////Playing the Game//////////
    int count = 0;
    while(player.isAlive()) { //player = alive : run game
      System.out.println(player.toString()); //print player health and Coordinates
      count = 0;
      count = countMonsters(player, monsters);
      System.out.println("You smell " + count + " monsters"); //print nearby monsters

      move(player, width); //check for movement and move if valid

      //check all monsters in the index and fight any in your room
      for (int i = 0; i < monsters.size(); i++) { 
        if (player.inSameRoom(monsters.get(i))) {
          System.out.println(player.toString() + " versus " + monsters.get(i).toString());
          fightMonsters(player, monsters.get(i)); //fight monsters

          if (monsters.get(i).isAlive()) {
            //if monster is alive then player should be dead
            System.out.println("You have perished");
            System.exit(0);
          }
          else {
            //Take monster's gold
            player.setGold(player.getGold() + monsters.get(i).getGold());
            monsters.remove(i); //remove monster from the monster array
          }
        }
        else continue;
      }
      //Fight and alive check before escape check to ensure player is alive to escape.
      if (!(player.isAlive())) {
        System.out.println("You have perished");
        System.exit(0);
      }
      
      else if (player.hasEscaped(width)) { //escape check
        System.out.println("You have escaped the dungeon with " + player.getGold() + " gold!");
        break;
      }
      
      else continue; //next turn
    }
  }

  static Player makePlayer() { //make player
    System.out.print("What is your name? ");
    String name = input.nextLine();
    Player player = new Player(name);
    return player;
  }

  static int getDungeonSize() {
    int width = 0;
    //While loop to ensure dungeon is size 5-10
    while ( !(5 <= width && width <= 10)) {
    System.out.print("How wide of a dungeon will you face? (5-10) ");
    width = input.nextInt();
      if (5 <= width && width <=10) {
        continue;
      }
      else {
       System.out.println("That is not a valid dungeon size!");
     }
    }
    return width;
  }

  static ArrayList<Player> makeMonsters(int width) { //making all monsters
    int totalMon = (width * width) / 6;
    ArrayList<Player> monsters = new ArrayList<Player>();
    
    for (int x = 1; x < totalMon + 1; x++) {
      int row = 0;
      int column = 0;
      //Second loop to ensure both values aren't zero (Monster won't spawn in (0,0))
      while ((row == 0) && (column == 0)) {
        row = (int) (Math.random() * (width+1));
        column = (int) (Math.random() * (width+1));
      }
      int gold = 50 + (int) (Math.random() * 51);
      //use monsters constructor and add to monsters array
      Player monster = new Player("Monster " + x, row, column, gold);
      monsters.add(monster);

    }
    return monsters;
  }

  static void fightMonsters(Player player, Player monster) {
    while (player.isAlive() && monster.isAlive()) {//check if both entities are alive
      int temp = 0;
      temp = player.hit(monster); //temp variable to display hit value
      System.out.println("You hit for " + temp);
      if (monster.isAlive()) { //monster only hits if monster is alive
        temp = monster.hit(player);
        System.out.println("You got hit for " + temp);
      }
      else ;
    }
  }

  static int countMonsters(Player player, ArrayList<Player> monsters) {
    int counter = 0;
    //check every monster in array if in an adjacent room, counter++
    for (int x = 0; x < monsters.size(); x++) {
      if (player.inAdjacentRoom(monsters.get(x))) {
        counter++;
      }
      else continue;
    }
    return counter;
  }

  static void move(Player player, int dungeonSize) {
    boolean flag = true;
    while (flag) { //will repeatedly prompt for a correct direction
      System.out.print("Which direction will you go? (North, East, South, West) ");
      String direction = input.nextLine();
      if (player.canMove(direction, dungeonSize)) {//checks if player can move (Player method)
        flag = false;
        if (direction.equalsIgnoreCase("north")) {//move player
          player.setRow(player.getRow() -1);
          player.setHealth(player.getHealth() - 2);
        }
        else if (direction.equalsIgnoreCase("east")) {
          player.setColumn(player.getColumn() + 1);
          player.setHealth(player.getHealth() - 2);
        }
        else if (direction.equalsIgnoreCase("south")) {
          player.setRow(player.getRow() + 1);
          player.setHealth(player.getHealth() - 2);
        }
        else if (direction.equalsIgnoreCase("west")) {
          player.setColumn(player.getColumn() - 1);
          player.setHealth(player.getHealth() - 2);
        }
      }
      else {
        System.out.println("You can't move that way!");
      }
    }
  }
}
