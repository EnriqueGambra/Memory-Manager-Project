package memorymanager;

import java.util.Scanner;

public class MemoryBlock {
    private int pid, processSize;
    private Scanner input = new Scanner(System.in);
    
    public MemoryBlock(){
    
    }
    
    public MemoryBlock(int pid, int processSize){
        this.pid = pid;
        this.processSize = processSize;
    }
    
    public void input(){
        System.out.print("Enter process id: ");
        this.pid = input.nextInt();
        System.out.print("Enter process size: ");
        this.processSize = input.nextInt();
    }
    
    public int getPid(){
        return pid;
    }
    
    public int getProcessSize(){
        return processSize;
    }
    
    public void setPid(int pid){
        this.pid = pid;
    }
    
    public void setProcessSize(int processSize){
        this.processSize = processSize;
    }
}
