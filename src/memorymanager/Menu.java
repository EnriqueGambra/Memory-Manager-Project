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
<<<<<<< HEAD
    private int maxForAll = 500;
    private boolean processAdded = true;
    private ArrayList<MemoryBlock> blocks = new ArrayList();
    private ArrayList<MemoryBlock> freeMemory = new ArrayList();
    private ArrayList<MemoryBlock> waitingQueue = new ArrayList();
    private ArrayList<MemoryBlock> removedProcesses = new ArrayList();
    private boolean maxNotReached = false;
=======
    static private int maxForAll = 500;
    private boolean processAdded = true;
    static private ArrayList<MemoryBlock> blocks = new ArrayList();
    private ArrayList<MemoryBlock> freeMemory = new ArrayList();
    private ArrayList<MemoryBlock> removedProcesses = new ArrayList();
    private boolean maxReached = false;
>>>>>>> master
    private boolean notFitInRemoved = false;
    
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
                System.out.println("You typed in the wrong number");
            }
            
            switch(menuChoice){
                case 1:
                    input();
<<<<<<< HEAD
                    
                        if(blocks.size() == 0){
                            System.out.println("Hi");
                            //maxNotReached = checkOverflow();
                            //if(maxNotReached == true)
                            //{
                            blocks.add(new MemoryBlock(this.pid,this.processSize, 0, this.processSize));
                            //}
                        }else
                        {
                            //maxNotReached = checkOverflow();
                            //if(maxNotReached == true)
                            //{
                            if(removedProcesses.size() > 0)
                            {
                                for(int i = 0; i < removedProcesses.size(); i++)
                                {
                                    if(removedProcesses.get(i).getProcessSize() >= processSize)
                                    {
                                        MemoryBlock newBlock = new MemoryBlock(this.pid, this.processSize, removedProcesses.get(i).getMin(), 
                                                                removedProcesses.get(i).getMin() + processSize);
                                        notFitInRemoved = true;
                                        System.out.println("In here!");
                                        System.out.println("PID = " + newBlock.getPid() + "Max = " + newBlock.getMax() + "Min = " + newBlock.getMin());
                                        blocks.trimToSize();
                                        blocks.add(newBlock);
                                        break;
                                    }
                                    break;
                                }
                                if(notFitInRemoved == true)
                                {
                                    continue;
                                }
                                else
                                {
                                    MemoryBlock newBlock = new MemoryBlock(this.pid,this.processSize, blocks.get(blocks.size() - 1).getMax(), 0);
                                    newBlock.setMax(newBlock.getMin() + newBlock.getProcessSize());
                                    blocks.add(newBlock);
                                }
                            }
                            else
                            {
                            MemoryBlock newBlock = new MemoryBlock(this.pid,this.processSize, blocks.get(blocks.size() - 1).getMax(), 0);
                            newBlock.setMax(newBlock.getMin() + newBlock.getProcessSize());
                            blocks.add(newBlock);
                            }
                        }
                        System.out.println(blocks.get(blocks.size() - 1).toString());
=======
                   
                        //System.out.println(blocks.get(blocks.size() - 1).toString());
>>>>>>> master
                    break;
                case 2:
                    break;
                case 3:
                    System.out.print("What process would you like to remove? ");
                    process = input.nextInt();
                    for(int i = 0; i < blocks.size(); i++) {
                        if(process == blocks.get(i).getPid()){
<<<<<<< HEAD
                            removeProcess(i);
=======
                            blocks.remove(i);
                            pids.remove(i);
                            System.out.println("removed successfully");
>>>>>>> master
                        }
                    }
                    
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println();
<<<<<<< HEAD
                    System.out.println("PID         Process Size        Min          Max");
                    for(int i = 0; i < blocks.size(); i++){
                        System.out.printf("%d           %d                   %d            %d\n", 
=======
                    System.out.println("PID\t\tProcess Size\t\tMin\t\tMax");
                    for(int i = 0; i < blocks.size(); i++){
                        System.out.printf("%d\t\t%d\t\t\t%d\t\t%d\n", 
>>>>>>> master
                                blocks.get(i).getPid(),
                                blocks.get(i).getProcessSize(),
                                blocks.get(i).getMin(),
                                blocks.get(i).getMax());  
<<<<<<< HEAD
                        //System.out.println("Blocks size = " + blocks.size());
                    }
                    
=======
                    }
>>>>>>> master
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
                    break;          
<<<<<<< HEAD
=======
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
                return;  
            }
        }
        
        maxReached = checkOverflow(processSize);
        
        if(!inArray && !maxReached){
            pids.add(pid);
        }
        
        if(blocks.isEmpty() && !inArray){
            blocks.add(new MemoryBlock(this.pid,this.processSize, 0, this.processSize));   
        }
        else
        {
            if(removedProcesses.size() > 0)
            {
                for(int i = 0; i < removedProcesses.size(); i++)
                {
                    if(removedProcesses.get(i).getProcessSize() >= processSize)
                    {
                        MemoryBlock newBlock = new MemoryBlock(this.pid, this.processSize, removedProcesses.get(i).getMin(), 
                                                removedProcesses.get(i).getMin() + processSize);
                        notFitInRemoved = true;
                        System.out.println("In here!");
                        System.out.println("PID = " + newBlock.getPid() + "Max = " + newBlock.getMax() + "Min = " + newBlock.getMin());
                        blocks.trimToSize();
                        blocks.add(newBlock);
                        break;
                    }
                    break;
                }   
            }
            else
            {
                MemoryBlock newBlock = new MemoryBlock(this.pid,this.processSize, blocks.get(blocks.size() - 1).getMax(), 0);
                newBlock.setMax(newBlock.getMin() + newBlock.getProcessSize());
                blocks.add(newBlock);
>>>>>>> master
            }
            
            
//            if(!maxReached && !inArray){
//                MemoryBlock newBlock = new MemoryBlock(this.pid,this.processSize, blocks.get(blocks.size() - 1).getMax(), 0);
//                newBlock.setMax(newBlock.getMin() + newBlock.getProcessSize());
//                blocks.add(newBlock);
//            }
        }
    }
    
<<<<<<< HEAD
    
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
=======
    public static boolean checkOverflow(int processSize){
        maxForAll = 500;
        for(int i = 0; i < blocks.size(); i++){
            maxForAll = maxForAll - blocks.get(i).getProcessSize();
            System.out.println("block pid " + blocks.get(i).getPid() + " max for all " + maxForAll);
        }
        maxForAll -= processSize;
        
        if(maxForAll < 0){
            System.out.println("max was reached");
            return true;
        }else{
            return false;
        }
>>>>>>> master
    }
    
    public void removeProcess(int i)
    {
        removedProcesses.add(blocks.get(i));
        System.out.println("Process " + blocks.get(i).getPid() + " was removed sucessfully!");
        blocks.remove(i);
    }
<<<<<<< HEAD
=======
    
>>>>>>> master
}