# Create your map
- Okay so I create the code for being pretty useful to use and create your map
- This is a doc for how to make it 

## Configure the map 
- First, you have to configure the map by creating a new class and add it to the MapSelector I will show you later

#### Create the class Map 
- Go in the package Game where is all the other Map create just stand by with the other 
*Map .java a name like "MyMap" etc... doesn't matter 
  
  #### The contents 
    - So first the class have to extends WindowInitializer create a constructor empty
    - And then you have to override 3 methods  in the super they will be call in this order 
      - **protected void setWall()**
      - **protected void setShop()**
      - **protected void setObject()**
    - You don't have to fill them for the moment except for setWall you have to put a player in first instruction ```player = new Player(this, new Coord(x,y));```
    - Of course for all the tutorial x y are x = line y = col and they all are an int 
    - the board is like a 2 dim board 0,0 is in the top left and BOARD_DIM BOARD_DIM bottom right if you didn't have change the config BOARD_DIM = 20
  #### Change the dim of the map
  - **You can also change the dimension of the map with override ```protected void setDimBoard(int dimBoard)``` and put super.setDimBoard(the value of the dim of the board you wan); but careful the icon will not work properly you have to create a new package with good dimensions**
    and override each icon you use to is proper version and dimension
  - if you just want to change the icon override setDimBoard ```super.setDimBoard(the same variable as your override methods)```
    #### put a icon 
    - make sur your icon is a png
    - in the override methods setDimBoard just do ```theIconOfWindowInitializerYouWantToChange = new javax.swing.ImageIcon(getClass().getResource("path/name.png"));```
    - and repeat for all the icon you want to change juste override a ImageIcon of WindowInitializer if you want to keep it easy
     
    #### link with the MapSelector 
    - Go to the java MapSelector who is in the same package as your map 
    I have make // comment where you have to put code in the selectionValue String[] 
      put a String that will describe your map to the player
    - Then go to the end of the class you should see other // comment here put the case of your map with the same 
    String you put in the selectionValue and then make a new (name of your map) and break 
    - you can look at the other case above if you don't succeed
    - **For try if the link is good, try to launch the main and select your map you should have a void map**
    

## fill the map
- **Warning** i do not recommend to put several enemy or shop or if it's a wall in the same case and just add one player and one king 
### setWall()
- Okay so the first statement will be to create a Player ```player = new Player(this, new Coord(x,y);``` x,y is the coord | but normaly you already done that

- Then you free to put wall like you want with ```board[i][j].setWall(mountainIcon);``` mountainIcon 
  is the default icon of the wall but if you don't like it you can change by load one of your icon
  
### setShop()  
- **Warning** put a shield in the shop is ```new ShieldBuy()```
- first you create your pnj with ```PnjMerchand pnj1 = new PnjMerchand();```
- then you add to him item to sell like :
    - ```pnj1.addItemToSell(new ViewHidden(this));```
    - ```pnj1.addItemToSell(new Potion(40));```
- You can put everything you want if is extends ObjectBuy so all the java in package item and  package buy     
- one your pnj has all you want use to put in the game ```putPnj(new Coord(x, y), pnj1, shopIcon);``` you can change pnjIcon if you want

### setObject()
- here you put all the Enemy or shield on the map if you want to change the icon of the enemy go tcheck section **Change the dim of the map** 
- you just only need ```putPnj(new Coord(x, y), new PnjKnight(), knightIcon);``` 
  here in the new you can put every mob of the package enemy, and after is the icon 
I advise you to use the same for all the same type of enemy

- **warning** for put a shield you have to do : ```board[x][y].putShield(shieldIcon);```
- if you want random enemy you have the methods ```putEnemyRandom()``` who in all the board put enemy randomly
  #### putEnemyRandom()
  - so first is an int who will select the number max of shield on the map who can randomly be put if it's 2 there is
    max 2 shield
  - second is a enum of Dificulty   if you put Dificulty.FREE the player can choose the difficulty or you can choose for him
    by put Difficulty.MEDIUM or Difficulty.EASY or Difficulty.HARD
### setCoin()
 - If you want that each Case has a coin you can override ```protected void setCoin(int valueMaxOfACoins)``` and then inside put
   ```super.setCoin(The value maximun your case can have);``` put 0 and there will be no coin you have a constant ```WindowInitializer.NUMBER_MAX_COIN_NORMAL```  for put a value for the coin pretty good 
   - if you put 30 each case will have a value between (1 30) ect...
### setBomb()
  - there is another methods you can override if you want to use ```protected void setBomb(int numberOfBomb)```you just have to put in super.setBomb(and an int )
    i have made some constant who will make the work pretty good but you can put the int you want, 0 if you don't want bomb 
    the int you put will make that  1/int case will be a bomb
    
**I hope you like this program if you have advice for upgrade it or make the create map easier tell me**
  

*if you create a good map send me and I put it in the code*
