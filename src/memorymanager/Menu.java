package memorymanager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Menu {
    private ArrayList<Integer> pids = new ArrayList();
    private boolean inArray = false;
    private Scanner input = new Scanner(System.in);
    
    
    static private int pid, processSize;
    private int min = 0;
    private int maxCapacity = 0;
    static private int maxForAll = 500;
    private boolean processAdded = false;
    static private ArrayList<MemoryBlock> blocks = new ArrayList();
    private ArrayList<MemoryBlock> removedProcesses = new ArrayList();
    static private ArrayList<MemoryBlock> waitingQueue = new ArrayList();
    private boolean maxReached = false;
    private boolean notFitInRemoved = false;
    
    public Menu(){
        Scanner input = new Scanner(System.in);
        int menuChoice = 0;
        int process = -1;
        boolean inArray = false;
        
        MemoryBlock block = new MemoryBlock() {};
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
                    firstFitInput();
                    break;
                case 2:
                    bestFitInput();
                    break;
                case 3:
                    System.out.print("What process would you like to remove? ");
                    process = input.nextInt();
                    for(int i = 0; i < blocks.size(); i++) {
                        if(process == blocks.get(i).getPid()){
                            removedProcesses.add(blocks.get(i));
                            blocks.remove(i);
                            pids.remove(i);
                            System.out.println("removed successfully");
                            int waitSize = waitingQueue.size();
                            if(waitingQueue.size() > 0)
                            {
                             for(int j = 0; j < waitSize; j++)
                             {
                                if(waitingQueue.size() > 0)
                                {
                                addInWaitingQueue();  
                                }
                             }
                            }
                        }
                    }
                    break;
                case 4:
                    if(waitingQueue.size() > 0)
                    {
                        System.out.println("PID\t\tProcess Size\t\tMin\t\tMax");
                        for(int i = 0; i < waitingQueue.size(); i++)
                        {
                            System.out.printf("%d\t\t%d\t\t\t%d\t\t%d\n", 

                                waitingQueue.get(i).getPid(),
                                waitingQueue.get(i).getProcessSize(),
                                waitingQueue.get(i).getMin(),
                                waitingQueue.get(i).getMax()); 
                        }
                    }
                    break;
                case 5:
                    System.out.println();
                    sortMemoryBlocks();
                    System.out.println(Integer.toString(blocks.size()) + " = size of blocks array");
                    System.out.println("PID\t\tProcess Size\t\tMin\t\tMax");
                    for(int i = 0; i < blocks.size(); i++){
                        System.out.printf("%d\t\t%d\t\t\t%d\t\t%d\n", 

                                blocks.get(i).getPid(),
                                blocks.get(i).getProcessSize(),
                                blocks.get(i).getMin(),
                                blocks.get(i).getMax()); 
                    }
                    break;
                case 6:
                    for(int i = 0; i < blocks.size(); i++)
                    {
                        compactMemory();
                    }
                    sortMemoryBlocks();
                    System.out.println("Memory compacted!");
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
                    break;          

            }
        }
    }
    
    
    public void firstFitInput(){
        System.out.print("Enter process id: ");
        pid = input.nextInt();
        System.out.print("Enter process size: ");
        processSize = input.nextInt();
        
        
        if(pid < 0){
            System.out.println("You CANNOT enter a pid number less than 1");
            firstFitInput();
        }
        
        if(processSize < 1){
            System.out.println("You CANNOT enter a process size less than 1");
            firstFitInput();
        }else if(processSize > 500){
            System.out.println("You CANNOT enter a process size greater than 500");
            firstFitInput();
        }
        
        //checks for pids, if they are in array already
        for(int i = 0; i < pids.size(); i++){
            if(pid == pids.get(i)){
                System.out.println("PID is in memory already");
                return;  
            }
        }
        
        maxReached = checkOverflow(processSize);
        
        if(maxReached){
            return;
        }
        
        if(!inArray && !maxReached){
            pids.add(pid);
        }
        
        if(blocks.isEmpty() && !inArray){
            blocks.add(new MemoryBlock(this.pid,this.processSize, 0, this.processSize));
            System.out.println("Inside blocksIsEmpty conditional");
        }
        else
        {
            System.out.println("Before if removedProcesses statement");
            if(removedProcesses.size() > 0 && !inArray)
            {
                System.out.println("Inside if removedProcesses, before for loop");
                for(int i = 0; i < removedProcesses.size(); i++)
                {
                    if(removedProcesses.get(i).getProcessSize() >= processSize && !inArray)
                    {
                        MemoryBlock newBlock = new MemoryBlock(this.pid, this.processSize, removedProcesses.get(i).getMin(), 
                                                removedProcesses.get(i).getMin() + processSize);
                        notFitInRemoved = true;
                        blocks.trimToSize();
                        blocks.add(newBlock);
                        removedProcesses.add(new MemoryBlock(this.pid, removedProcesses.get(i).getProcessSize()-newBlock.getProcessSize(),
                                                        newBlock.getMax(), blocks.get(i + 1).getMin()));
                        removedProcesses.remove(i);
                        removedProcesses.trimToSize();
                        processAdded = true;
                        break;
                    }
                    //break;
                }   
            }
            if(processAdded == true)
            {
                
            }
            else
            {
                MemoryBlock newBlock = new MemoryBlock(this.pid,this.processSize, blocks.get(blocks.size() - 1).getMax(), 0);
                newBlock.setMax(newBlock.getMin() + newBlock.getProcessSize());
                blocks.add(newBlock);
            }
           
        }
        sortMemoryBlocks();
    }
  
    public static boolean checkOverflow(int processSize){
        maxForAll = 500;
        for(int i = 0; i < blocks.size(); i++){
            maxForAll = maxForAll - blocks.get(i).getProcessSize();
            System.out.println("block pid " + blocks.get(i).getPid() + " max for all " + maxForAll);
        }
        maxForAll -= processSize;
        
        if(maxForAll < 0){
            MemoryBlock newBlock = new MemoryBlock(pid, processSize, 0, processSize);
            System.out.println("Process " + Integer.toString(pid) + " can't fit within main memory. It has been moved to the waiting queue!");
            waitingQueue.add(newBlock);
            return true;
        }else{
            return false;
        }

    }
    
    public void removeProcess(int i)
    {
        removedProcesses.add(blocks.get(i));
        System.out.println("Process " + blocks.get(i).getPid() + " was removed sucessfully!");
        blocks.remove(i);
        pids.remove(i);
    }

    public void sortMemoryBlocks() 
    {
        SortBlocks comparator = new SortBlocks();
        Collections.sort(blocks, comparator);
    }

    public void addInWaitingQueue() 
    {
        for(int i = 0; i < waitingQueue.size(); i++)
        {
            System.out.println("Witin waitingQueue for loop!");
            for(int j = 0; j < removedProcesses.size(); j++)
            {
                System.out.println("Within removedProcesses for loop!");
                if(waitingQueue.get(i).getProcessSize() <= removedProcesses.get(j).getProcessSize())
                {
                    waitingQueue.get(i).setMin(removedProcesses.get(j).getMin());
                    waitingQueue.get(i).setMax(removedProcesses.get(j).getMin() + waitingQueue.get(i).getProcessSize());
                    blocks.add(waitingQueue.get(i));
                    System.out.println("Process " + Integer.toString(waitingQueue.get(i).getPid()) + " has been moved from the waiting queue into"
                                        + " main memory!");
                    removedProcesses.add(new MemoryBlock(this.pid, removedProcesses.get(j).getProcessSize()-waitingQueue.get(i).getProcessSize(),
                                                        waitingQueue.get(i).getMax(), waitingQueue.get(i).getMax()
                                                                + waitingQueue.get(i).getProcessSize()));
                    waitingQueue.remove(i);
                    removedProcesses.remove(j);
                    //Might have to add trimToSize for both arraylists
                }
            }
        }
    }

    public void compactMemory() 
    {
        int almostOut = blocks.size()-1;
        
        for(int i = 0; i < blocks.size(); i++){

            if( i == 0 ){
                blocks.get(i).setMin(0);
                blocks.get(i).setMax(blocks.get(i).getProcessSize());
            }
            if( i <= almostOut && i > 0){
                blocks.get(i).setMin(blocks.get(i-1).getMax());
                blocks.get(i).setMax(blocks.get(i-1).getMax() + blocks.get(i).getProcessSize());
            }
        }
        blocks.trimToSize();
    }

    private void bestFitInput() 
    {
        System.out.print("Enter process id: ");
        pid = input.nextInt();
        System.out.print("Enter process size: ");
        processSize = input.nextInt();
        
        
        if(pid < 0){
            System.out.println("You CANNOT enter a pid number less than 1");
            firstFitInput();
        }
        
        if(processSize < 1){
            System.out.println("You CANNOT enter a process size less than 1");
            firstFitInput();
        }else if(processSize > 500){
            System.out.println("You CANNOT enter a process size greater than 500");
            firstFitInput();
        }
        
        //checks for pids, if they are in array already
        for(int i = 0; i < pids.size(); i++){
            if(pid == pids.get(i)){
                System.out.println("PID is in memory already");
                return;  
            }
        }
        
        maxReached = checkOverflow(processSize);
        
        if(maxReached){
            return;
        }
        
        if(!inArray && !maxReached){
            pids.add(pid);
        }
        
        if(blocks.isEmpty() && !inArray){
            blocks.add(new MemoryBlock(this.pid,this.processSize, 0, this.processSize));
            System.out.println("Inside blocksIsEmpty conditional");
        }
        else
        {
            System.out.println("Before if removedProcesses statement");
            if(removedProcesses.size() > 0 && !inArray)
            {
                int nextRemovedBlockProcessSize = 500;
                int currentProcessSize = processSize;
                int sizeBetweenNextBlock = 500;
                MemoryBlock blockBestFit = new MemoryBlock();
                
                boolean foundBestFit = false;
                for(int i = 0; i < removedProcesses.size(); i++)
                {
                    nextRemovedBlockProcessSize = removedProcesses.get(i).getProcessSize();
                    if(currentProcessSize <= nextRemovedBlockProcessSize)
                    {
                        if(sizeBetweenNextBlock >= nextRemovedBlockProcessSize)
                        {
                            sizeBetweenNextBlock = nextRemovedBlockProcessSize;
                            blockBestFit = removedProcesses.get(i);
                            foundBestFit = true;
                            System.out.printf("best min: %d| best max: %d\n",blockBestFit.getMin(), blockBestFit.getMax());
                        }
                    }
                }
                System.out.println("Inside if removedProcesses, before for loop");
                for(int i = 0; i < removedProcesses.size(); i++)//For best fit, its going to have to loop and look through each removedProcess(free space)                                        
                {                                               //spot, and then calculate which free space allows it to have the least amount of space
                    if(removedProcesses.get(i).getProcessSize() >= processSize && !inArray)//left over after it is inserted...
                    {
                        if(foundBestFit = true)
                        {
                            MemoryBlock newBlock = blockBestFit;
                            notFitInRemoved = true;
                            //blocks.trimToSize();
                            blocks.add(newBlock);
                            removedProcesses.add(new MemoryBlock(this.pid, 
                                                removedProcesses.get(i).getProcessSize()-newBlock.getProcessSize(),
                                                newBlock.getMax(),
                                                blocks.get(i + 1).getMin()));
                            removedProcesses.remove(i);
                            removedProcesses.trimToSize();
                            processAdded = true;
                            foundBestFit = false;
                            break;
                        }
                    }
                }   
            }
            if(processAdded == true)
            {
                
            }
            else
            {
                MemoryBlock newBlock = new MemoryBlock(this.pid,
                                                      this.processSize, 
                                                      blocks.get(blocks.size() - 1).getMax(),
                                                      0);
                newBlock.setMax(newBlock.getMin() + newBlock.getProcessSize());
                blocks.add(newBlock);
            }
        }
        sortMemoryBlocks();
    }

}