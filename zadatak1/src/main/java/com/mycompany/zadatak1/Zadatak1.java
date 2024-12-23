package com.mycompany.zadatak1;
public class Zadatak1{
    
    static int first = 0;
    
    public static void main(String[] args){
        
        boolean runGame = true;
        
        while(runGame){
                
            
                System.out.print("Starting a new game\n");
                Game p1 = new Game();
                if(first == 0){
                    p1.StartMenu();
                    first++;
                }
                else{
                    p1.Layout();
                    p1.Obsticles();
                    p1.RefreshGame();
                }
               

                while(p1.isGameRunning){
                    try {
                        Thread.sleep(1000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while(p1.isClicked){
                     try {
                        Thread.sleep(1000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                runGame = p1.endGame;
                  
                    
                    
                
                
        }             
    }
  }
