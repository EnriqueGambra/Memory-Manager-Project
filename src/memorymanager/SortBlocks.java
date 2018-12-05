
package memorymanager;

import java.util.Comparator;


public class SortBlocks implements Comparator<MemoryBlock>
{

    @Override
    public int compare(MemoryBlock t, MemoryBlock t1) 
    {
        return t.getMin() - t1.getMax();//Sorts the block that has the smaller max than the previous
    }
    
}
