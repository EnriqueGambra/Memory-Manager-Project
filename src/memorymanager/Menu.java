package memorymanager;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Integer> pids = new ArrayList();
    private boolean inArray = false;
    private Scanner input = new Scanner(System.in);
    
    
    private int pid, processSize;
    private int min = 0;
    private int maxCapacity = 0;
    private int maxForAll = 500;
    private boolean processAdded = true;
    private ArrayList<MemoryBlock> blocks = new ArrayList();
    private ArrayList<MemoryBlock> freeMemory = new ArrayList();
    private ArrayList<MemoryBlock> waitingQueue = new ArrayList();
    private boolean maxNotReached = false;
    
    public Menu(){
        Scanner input = new Scanner(System.in);
        int menuChoice = 0;
        int process = -1;
        boolean inArray = false;
        
        MemoryBlock block = new MemoryBlock();
        while(menuChoice != 7){
            System.out.println("");
            System.out.println("************************************************************");
            System.out.println("* WELCOME TO ENRIQUE GAMBRA'S & JORGE DIAZ' MEMORY MANAGER *");
            System.out.println("************************************************************");
            System.out.println("*                                                          *");
            System.out.println("*            Select from the following choices             *");
            System.out.println("*            ---------------------------------             *");
            System.out.println("*            1. Insert process - First Fit                 *");
            System.out.println("*            2. Insert process - Best Fit                  *");
            System.out.println("*            3. Remove process                             *");
            System.out.println("*            4. View waiting queue                         *");
            System.out.println("*            5. View processes in Memory                   *");
            System.out.println("*            6. Compact memory                             *");
            System.out.println("*            7. EXIT                                       *");;
            System.out.println("*                                                          *");
            System.out.println("*            NOTE: Memory max capacity 500kb               *");
            System.out.println("*                                                          *");
            System.out.println("************************************************************");
            System.out.print("Enter your choice: ");
            try{
                menuChoice = input.nextInt();
            }catch(Exception e){
                System.out.println("You typed in the wrong number, Sister Jane");
            }
            
            switch(menuChoice){
                case 1:
                    input();
                    
                        if(blocks.size() == 0){
                            maxNotReached = checkOverflow();
                            if(maxNotReached == true)
                            {
                            blocks.add(new MemoryBlock(this.pid,this.processSize, 0, this.processSize));
                            }
                        }else
                        {
                            maxNotReached = checkOverflow();
                            if(maxNotReached == true)
                            {
                            MemoryBlock newBlock = new MemoryBlock(this.pid,this.processSize, blocks.get(blocks.size() - 1).getMax(), 0);
                            newBlock.setMax(newBlock.getMin() + newBlock.getProcessSize());
                            blocks.add(newBlock);
                            }
                        }
                        System.out.println(blocks.get(blocks.size() - 1).toString());
                    break;
                case 2:
                    break;
                case 3:
                    System.out.print("What process would you like to remove? ");
                    process = input.nextInt();
                    for(int i = 0; i < blocks.size(); i++) {
                        if(process == blocks.get(i).getPid()){
                            blocks.remove(i);
                            System.out.println("removed successefully");
                        }
                    }
                    
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println();
                    System.out.println("PID         Process Size        Min          Max");
                    for(int i = 0; i < blocks.size(); i++){
                        System.out.printf("%d           %d                   %d            %d\n", 
                                blocks.get(i).getPid(),
                                blocks.get(i).getProcessSize(),
                                blocks.get(i).getMin(),
                                blocks.get(i).getMax());  
                    }
                    
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
                    break;          
            }
        }
    }
    
    
    public void input(){
        System.out.print("Enter process id: ");
        pid = input.nextInt();
        System.out.print("Enter process size: ");
        processSize = input.nextInt();
        
        
        if(pid < 0){
            System.out.println("You CANNOT enter a pid number less than 1");
            input();
        }
        
        if(processSize < 1){
            System.out.println("You CANNOT enter a process size less than 1");
            input();
        }else if(processSize > 500){
            System.out.println("You CANNOT enter a process size greater than 500");
            input();
        }
        
        //checks for pids, if they are in array already
        for(int i = 0; i < pids.size(); i++){
            if(pid == pids.get(i)){
                System.out.println("PID is in memory already");
                inArray = true;
                
            }
        }
        if(!inArray){
            pids.add(pid);
            System.out.println("PID added successfully");
        }
    }
    
    public boolean checkOverflow()
    {
        maxForAll = 500;
        for(int i = 0; blocks.size() > i; i++)
        {
            maxForAll = maxForAll - blocks.get(i).getProcessSize();
        }
        
        if(maxCapacity < maxForAll)
        {
            System.out.println("Process " + pid + " does not fit within the memory. It is being added to the waiting queue." );
            waitingQueue.add(new MemoryBlock(this.pid,this.processSize, 0, 0));
            return false;
        }
        return true;
    }
}