
//Player Class

class Player {
  private String name;
  private int health;
  private int maxDamage;
  private int row; //y-axis
  private int column; //x-axis
  private int gold;


//Player Constructor
  public Player(String newName) {
    name = newName;
    health = 100;
    maxDamage = 10;
    row = 0;
    column = 0;
    gold = 0;
  }

//Monster Constructor
  public Player(String newName, int posRow, int posColumn, int newGold) {
    name = newName;
    health = 25;
    maxDamage = 5;
    row = posRow;
    column = posColumn;
    gold = newGold;
  }

  public int hit(Player other) {//random number between 1 and maxDamage
    int temp = ((int) (Math.random()* (this.getMaxDam()) + 1));
    other.setHealth(other.getHealth() - temp);
    return temp;
  }

  public String toString() { //return player name, health, and coordinates
    return name + " at (" + this.getColumn() + "," + this.getRow() + ") with " + this.getHealth() + " health";
  }

  public boolean isAlive() {//alive check
    if (this.getHealth() > 0) {
      return true;
    }
    else return false;
  }

  //Player will always call method (using "this") and check monsters position
  public boolean inSameRoom(Player monster) {
    if ((this.getRow() == monster.getRow()) && (this.getColumn() == monster.getColumn())) {
      return true;
    }
    else return false;
  }
  //Player will always call method (using "this") and check monsters position
  public boolean inAdjacentRoom(Player monster) {
    if ((this.getRow() == monster.getRow()) && ((this.getColumn() + 1 == monster.getColumn()) || (this.getColumn() - 1 == monster.getColumn()))) {
      return true;
    }
    else if ((this.getColumn() == monster.getColumn()) && ((this.getRow() + 1 == monster.getRow()) || ( this.getRow() - 1 == monster.getRow()))) {
      return true;
    }
    else return false;
  }
  //Player will always call method (using "this") and check position = (n,n)
  public boolean hasEscaped(int dungeonSize) {
    if ((this.getRow() == dungeonSize) && (this.getColumn() == dungeonSize)) {
      return true;
    }
    else return false;
  }
  //Player will always call method (using "this") and check position vs future position
  public boolean canMove(String direction, int dungeonSize) {
    if (direction.equalsIgnoreCase("north")) {
      if (this.getRow() - 1 < 0) return false;
      else return true;
    }
    else if (direction.equalsIgnoreCase("east")) {
      if (this.getColumn() + 1 > dungeonSize) return false;
      else return true;
    }
    else if (direction.equalsIgnoreCase("south")) {
      if (this.getRow() + 1 > dungeonSize) return false;
      else return true;
    }
    else if (direction.equalsIgnoreCase("west")) {
      if (this.getColumn() - 1 < 0) return false;
      else return true;
    }
    else return false; 
  }
//Getters and Setters
  public int getHealth() {
    return health;
  }
  
  public void setHealth(int newHealth) {
    health = newHealth;
  }

  public int getMaxDam() {
    return maxDamage;
  }

  public void setMaxDam(int newMaxDam) {
    maxDamage = newMaxDam;
  }

  public String getName() {
    return name;
  }

  public void setName(String newName) {
    name = newName;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int newRow) {
    row = newRow;
  }

  public int getColumn() {
    return column;
  }
  public void setColumn(int newColumn) {
    column = newColumn;
  }
  public int getGold() {
    return gold;
  }
  public void setGold(int newGold) {
    gold = newGold;
  }

}
