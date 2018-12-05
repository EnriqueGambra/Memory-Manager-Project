package memorymanager;

import java.util.ArrayList;
import java.util.Scanner;

public  class MemoryBlock  {
    private int pid, processSize, min, max;
    
    public MemoryBlock(){
    
    }
    
    public MemoryBlock(int pid, int processSize, int min, int max){
        this.pid = pid;
        this.processSize = processSize;
        this.min = min;
        this.max = max;
    }
    
    public int getProcessSize()
    {
        return processSize;
    }
    public int getPid(){
        return pid;
    }
    
    public void setPid(int pid){
        this.pid = pid;
    }
    
    public void setProcessSize(int processSize){
        this.processSize = processSize;
    }
    
    public int getMin(){
        return min;
    } 
    
    public int getMax(){
        return max;
    } 
    
    public void setMin(int min){
        this.min = min;
    }
    
    public void setMax(int max){
        this.max = max;
    }
    
    public String toString()
    {
        String s = "";
        s += this.pid + "\n";
        s += this.processSize + "\n";
        s += this.min + "\n";
        s += this.max + "\n";
        return s;
    }

}