/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadatak1;



import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game {
    
      
    Random rn = new Random();
    //variables
    private int counter = 0;
    private final int sleep_time=150;
    private int timer=100;
    
    private final int map_size_row = 20;
    private final int map_size_column = 50;
    private final int mapa[][] = new int[map_size_column][map_size_row];
    
    //player starting positions
    private int player_y = 3;
    private int player_x = 0;
    
    //player score
    private int level=1;
    private int lifes=2;
    
    //markers
    public boolean isGameRunning = true;
    public boolean isClicked =true;
    public boolean endGame = true;
    
    //main frame declaration
    private final JFrame frame = new JFrame("Igrica Game");
    
    //panels
    private final JPanel buttonPanel = new JPanel();
    
    private final JPanel gamePanel = new JPanel();
    
    //label declaration
    private JLabel levelLabel = new JLabel("Level: "+level);
    private JLabel lifesLabel = new JLabel("Lifes: "+lifes);
    
    private JLabel[][] labels = new JLabel[map_size_column][map_size_row];
    
    
    
    //Images
    private final ImageIcon grassIcon = new ImageIcon("C:\\Users\\ognje\\Desktop\\zadatak1\\src\\main\\resources\\grass.png");  // Replace with your image path
    private final ImageIcon wallIcon = new ImageIcon("C:\\Users\\ognje\\Desktop\\zadatak1\\src\\main\\resources\\wall.png"); 
    private final ImageIcon playerIcon = new ImageIcon("C:\\Users\\ognje\\Desktop\\zadatak1\\src\\main\\resources\\player.png"); 
    private final ImageIcon lifeIcon = new ImageIcon("C:\\Users\\ognje\\Desktop\\zadatak1\\src\\main\\resources\\life.png"); 
    //private final ImageIcon shieldIcon = new ImageIcon("C:\\Users\\ognje\\OneDrive\\Documents\\NetBeansProjects\\zadatak1\\src\\main\\resources\\shield.png"); 
    private final ImageIcon player_hitIcon = new ImageIcon("C:\\Users\\ognje\\Desktop\\zadatak1\\src\\main\\resources\\player_hit.png"); 
    
    public Clip backgrounClip;
    
    //sounds
    
    public void BackgroundSound() {
    new Thread(()-> {
        
            try {
                // Specify the path to your sound file
                File soundFile = new File("C:\\Users\\ognje\\Desktop\\zadatak1\\backgroundd.wav");

                // Get an AudioInputStream from the sound file
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                // Get a Clip object from the AudioInputStream
                backgrounClip = AudioSystem.getClip();
                backgrounClip.open(audioStream);

                // Start playing the sound
                backgrounClip.start();
                // Optionally, you can make it loop:
                backgrounClip.loop(Clip.LOOP_CONTINUOUSLY);  // Uncomment to loop

                // Wait for the sound to finish before continuing
                Thread.sleep(backgrounClip.getMicrosecondLength() / 1000); // Sleeps until the sound ends

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        
    }).start();  // This starts the thread
}
    
    public void MoveSound(){
        
        new Thread(()-> {
        
            try {
                // Specify the path to your sound file
                File soundFile = new File("C:\\Users\\ognje\\Desktop\\zadatak1\\move.wav");

                // Get an AudioInputStream from the sound file
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                // Get a Clip object from the AudioInputStream
                Clip moveClip;
                moveClip = AudioSystem.getClip();
                moveClip.open(audioStream);

                // Start playing the sound
                moveClip.start();

                // Optionally, you can make it loop:
                //clip.loop(Clip.LOOP_CONTINUOUSLY);  // Uncomment to loop

                // Wait for the sound to finish before continuing
                Thread.sleep(moveClip.getMicrosecondLength() / 1000); // Sleeps until the sound ends

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        
    }).start();
    
    }
    
    public void LostSound(){
        
        new Thread(()-> {
        
            try {
                // Specify the path to your sound file
                File soundFile = new File("C:\\Users\\ognje\\Desktop\\zadatak1\\lost.wav");

                // Get an AudioInputStream from the sound file
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                // Get a Clip object from the AudioInputStream
                Clip lostClip;
                lostClip = AudioSystem.getClip();
                lostClip.open(audioStream);

                // Start playing the sound
                lostClip.start();

                // Optionally, you can make it loop:
                //clip.loop(Clip.LOOP_CONTINUOUSLY);  // Uncomment to loop

                // Wait for the sound to finish before continuing
                Thread.sleep(lostClip.getMicrosecondLength() / 1000); // Sleeps until the sound ends

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        
    }).start();
    
    }
    
    public void LifeSound(){
        new Thread(()-> {
        
            try {
                // Specify the path to your sound file
                File soundFile = new File("C:\\Users\\ognje\\Desktop\\zadatak1\\life.wav");

                // Get an AudioInputStream from the sound file
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                // Get a Clip object from the AudioInputStream
                Clip lifeClip;
                lifeClip = AudioSystem.getClip();
                lifeClip.open(audioStream);

                // Start playing the sound
                lifeClip.start();

                // Optionally, you can make it loop:
                //clip.loop(Clip.LOOP_CONTINUOUSLY);  // Uncomment to loop

                // Wait for the sound to finish before continuing
                Thread.sleep(lifeClip.getMicrosecondLength() / 1000); // Sleeps until the sound ends

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        
    }).start();
    
    
        
    
    }
   
    public void HitSound(){
        new Thread(()-> {
        
            try {
                // Specify the path to your sound file
                File soundFile = new File("C:\\Users\\ognje\\Desktop\\zadatak1\\hit.wav");

                // Get an AudioInputStream from the sound file
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                // Get a Clip object from the AudioInputStream
                Clip hitClip;
                hitClip = AudioSystem.getClip();
                hitClip.open(audioStream);

                // Start playing the sound
                hitClip.start();

                // Optionally, you can make it loop:
                //clip.loop(Clip.LOOP_CONTINUOUSLY);  // Uncomment to loop

                // Wait for the sound to finish before continuing
                Thread.sleep(hitClip.getMicrosecondLength() / 1000); // Sleeps until the sound ends

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        
    }).start();
    
    
        
    
    }
   
    
    
    public void StartMenu(){
    
        JFrame startFrame = new JFrame("New game");
        startFrame.setSize(250,300);
        startFrame.setLocationRelativeTo(null);
        startFrame.setResizable(true);
        
        
        JPanel startPanel = new JPanel();
        
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        
        JButton startButton = new JButton("Start a game");
        JButton scoreButton = new JButton("ScoreBoard");
        JButton exitButton = new JButton( "Exit");
       
        startPanel.setBorder(BorderFactory.createEmptyBorder(50, 25, 50, 25));
        
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centers horizontally
        scoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        startPanel.add(startButton);
        startPanel.add(Box.createVerticalStrut(20));
        startPanel.add(scoreButton);
        startPanel.add(Box.createVerticalStrut(20));
        startPanel.add(exitButton);
        
        startFrame.add(startPanel);
        startFrame.setVisible(true);
        
        startButton.addActionListener(e -> {
            startFrame.dispose();
            Layout();
            Obsticles();
            RefreshGame();
        });
        scoreButton.addActionListener(e -> {
            startFrame.dispose();
            ScoreList();
        });
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        
        
        
    }
    
    
    public void Obsticles(){
        
        //0-green/grass
        //1-wall/brown
        //2-player/red
        //3-lifes/dark red
        
         //setting whole array to green
          for(int i = 0; i < mapa.length;i++){
            
            for( int j =0;j<mapa[i].length;j++){
                
                mapa[i][j]=0;
                
            }
        }
          //placing wall and lifes
        if(level!=10){  
        for(int i = 5; i < mapa.length;i=i+5){
            int rnInt = rn.nextInt(2,map_size_row);
            for( int j =0;j<mapa[i].length;j++){
                //placing random lifes
                if((level % 1 == 0)&&(i%rnInt==2)){
                    mapa[i][j]=1;
                    mapa[i][rnInt]=0;
                    mapa[i][rnInt-1]=3;
                    mapa[i][rnInt-2]=0;
                }else{ // placing wall 
                mapa[i][j]=1;
                mapa[i][rnInt]=0;
                mapa[i][rnInt-1]=0;
                mapa[i][rnInt-2]=0;
                }
            }
        }}
        else{//whole level just picking lifes
            for(int i = 2; i < mapa.length;i=i+2){
                
                int rnInt = rn.nextInt(map_size_row);
                
                for( int j =0;j<mapa[i].length;j++){

                    mapa[i][rnInt]=3;
            }
        }
        }
         
     }
    
    
    public void Layout(){
     
     //main frame settings
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(map_size_row*10+20, map_size_column*10+80);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
        //button definitions
        JButton dugme1 = new JButton("L");
        JButton dugme2 = new JButton("D");
        
        
        dugme1.setPreferredSize(new Dimension(44,26));
        dugme2.setPreferredSize(new Dimension(44,26));
       //adding components to button panel
        buttonPanel.add(dugme1);
        buttonPanel.add(dugme2);
        buttonPanel.add(levelLabel);
        buttonPanel.add(lifesLabel);
        //setting panel settings fort map
        gamePanel.setLayout(new GridLayout(map_size_column, map_size_row));
        
        //adding components to frame
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        
        
        
        // button 1 on click action 
        dugme1.addActionListener(e -> {
            System.out.println("Lijevo button clicked");
            MoveSound();
            //checking whats on left side of current player position
            if(player_y != 0){
                switch(mapa[player_x][player_y-1]){
                
                    case 1 -> {}//in case its wall, do nothing
                    case 3 -> {//in case its life
                        player_y--;
                        lifes++;
                        mapa[player_x][player_y]=0;
                        System.out.println("lifes: "+lifes);
                    }
                    default ->{//else green
                        player_y--;
                    }
                }
                
            }
        });

        // button 2 on click action 
        dugme2.addActionListener(e -> {
            MoveSound();
            System.out.println("right button clicked");
            //checking whats on right side of current player position
            if(player_y != (map_size_row-1)){
                switch(mapa[player_x][player_y+1]){
                
                    case 1 -> {}//in case its wall, do nothing
                    case 3 -> {//in case its life
                        player_y++;
                        lifes++;
                        mapa[player_x][player_y]=0;
                        System.out.println("lifes: "+lifes);
                    }
                     default ->{//else green
                        player_y++;
                    }
                }
            }
     });
        //keys for buttons, ALT+Key
        dugme1.setMnemonic(KeyEvent.VK_A);
        dugme2.setMnemonic(KeyEvent.VK_D);
        
        
        
     }
   
    
    public void RefreshGame(){
         
        
         new Thread(() -> {
            
            try {
                BackgroundSound();
                
                
                
                // Start the game loop and updates map
                while (lifes!=0) { 
                    
                    SwingUtilities.invokeLater(()->{
                        
                    gamePanel.removeAll();
                    counter = counter + timer;
                    //adding coresponding icons to array values
                    for (int i=0; i < map_size_column; i++) {
                        for (int j = 0; j < map_size_row; j++) {
                            labels[i][j] = new JLabel();
                            if(mapa[i][j]==0){
                                
                                labels[i][j].setIcon(grassIcon);
                            }else if(mapa[i][j]==3){
                                labels[i][j].setIcon(lifeIcon); 
                            }
                            else{
                            labels[i][j].setIcon(wallIcon);
                            
                            }
                            gamePanel.add(labels[i][j]);
                        }

                    }
                    //player moving in y direction
                    if(counter>500){
                        if (player_x!=map_size_column){
                            //in case it hits the wall - losing life
                            if(mapa[player_x+1][player_y]==1){
                                HitSound();
                                lifes--;
                                System.out.println(lifes);
                                labels[player_x+1][player_y].setIcon(player_hitIcon); 
                                ++player_x;  
                                lifesLabel.setText("Lifes: "+lifes);
                            }
                            // in case its life
                            if(mapa[player_x+1][player_y]==3){
                                LifeSound();
                                lifes++;
                                System.out.println(lifes);
                                mapa[player_x+1][player_y]=0;
                                lifesLabel.setText("Lifes: "+lifes);
                                
                            }
                            player_x++;
                            
                        }//when it reaches end of y direction
                        if(player_x ==map_size_column-1){
                            player_x=0;
                            timer=timer+18;
                            level++;
                            
                            Obsticles();
                            
                        }
                        counter=0;
                    }
                    labels[player_x][player_y].setIcon(playerIcon);
                    //updating the map
                    gamePanel.revalidate();
                    gamePanel.repaint();
                    
                    });
                    Thread.sleep(sleep_time);
                      
                }//went game ends
                RestartGame();
                isGameRunning=false;
                backgrounClip.stop();
                LostSound();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            
        }).start(); // Start the thread
         
         
     }
              
    
    public void DisposeWindow(JFrame x,JFrame y){
        
        x.dispose();
        y.dispose();
    
    }
    
        
     public void RestartGame(){
    
    //actiates after you lose the game
    if(lifes == 0){
                    
        //opens new widow
        JFrame end = new JFrame("");
        end.setSize(200,200);
        end.setLocationRelativeTo(frame);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //defining buttons 
        JButton restartButton = new JButton("Pokreni ponovo");
        JButton scoreButton = new JButton("Score");
        JButton exitButton = new JButton("Izadji");
        //defining label
        JLabel restartLabel = new JLabel("Izgubili ste", SwingConstants.CENTER);
               
        //defining panel
        JPanel restartPanel = new JPanel();
        
        restartPanel.setLayout(new BoxLayout(restartPanel, BoxLayout.Y_AXIS));
        
        
        restartPanel.add(Box.createVerticalStrut(10));
        restartPanel.add(restartButton);
        restartPanel.add(Box.createVerticalStrut(5));
        restartPanel.add(scoreButton);
        restartPanel.add(Box.createVerticalStrut(5));
        restartPanel.add(exitButton);
        
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centers horizontally
        scoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //adding components to frame
        end.add(restartLabel, BorderLayout.NORTH);
        end.add(restartPanel, BorderLayout.CENTER);
        
        
        //setting visibility 
        end.setAlwaysOnTop(true);
        end.setVisible(true);
        
        //defining actions on buttons
        restartButton.addActionListener(e -> {
            //closes both frames and starts game again
            System.out.println("Restart button clicked");
            isClicked= false;
            DisposeWindow(end,frame);

        });
        scoreButton.addActionListener(e -> {
            //closes both frames and ends the game
            System.out.println("End button clicked");
            isClicked= false;
            endGame = false;
            isGameRunning=false;
            
            DisposeWindow(end,frame);
            
            ScoreBoard();
        });
        exitButton.addActionListener(e -> {
            //closes both frames and starts game again
            System.out.println("exit button clicked");
            System.exit(0);
        });

        
    }   
    }
    
     
    public void ScoreList(){
    
        JFrame score_list = new JFrame("Score list");
            score_list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            score_list.setSize(200,600);
            score_list.setLocationRelativeTo(null);
            
            JTextArea scoreText = new JTextArea();
            scoreText.setEditable(false);
            try (Scanner scanner = new Scanner(new File("text.txt"))) {
            // Read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();                
                scoreText.append(line+"\n");// Print each line
            }
            } catch (FileNotFoundException ex) {
                // Handle error (file not found)
                ex.printStackTrace();
                System.out.println("Error reading from file: " + ex.getMessage());
            }
            
            score_list.add(scoreText, BorderLayout.CENTER);
            
            
            score_list.setVisible(true);
        
    }
    
    
    public void ScoreBoard(){
        //creates a new frame
        JFrame textFrame = new JFrame();
        textFrame.setSize(200,80);
        textFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        textFrame.setLocationRelativeTo(null);
        
        //creating inside frame
        JTextField textField = new JTextField(10);
        JLabel name_label = new JLabel("Upisi ime");
        JButton text_button = new JButton("ENTER");
        
        
        
        
        
        textFrame.add(name_label, BorderLayout.NORTH);
        textFrame.add(textField,BorderLayout.CENTER);
        textFrame.add(text_button,BorderLayout.EAST);
        
        textFrame.setVisible(true);
        
       
        text_button.setMnemonic(KeyEvent.VK_ENTER);
        text_button.addActionListener(e->{
        
            String input = textField.getText();
            
            
            
            try {
                FileWriter writer = new FileWriter("text.txt",true);
                writer.write("Player: "+input+"; Level: "+level+"\n");  // Write the text to the file
                writer.close(); 
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            textFrame.dispose();
            
            
            ScoreList();
        
        });
        
        
    
    
    } 
}
