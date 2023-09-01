package project4;

import java.util.ArrayList;
/**
 * This class represents a hiker traveling down the mountain. An object of this class 
 * is capable of keeping track of all the supplies that the hiker has in their 
 * possession. This information is updated as the hiker travels along a trail 
 * and consumes supplies (by either eating along the way, or using the tools to clear 
 * the path, or cross the river).  
 * 
 * @author Jessy Huang
 */
public class Hiker {
    // supply list of the hiker
    private ArrayList<String> supplyList;
    // hiker path
    private ArrayList<RestStop> path;

    /**
     * construtor.
     */
    public Hiker() {
        supplyList = new ArrayList<String>();
        path = new ArrayList<RestStop>();

    }

    /**
     * Getter of private variable {@code}path
     * @return, path of the Hiker.
     */
    public ArrayList<RestStop> getPath() {
        return path;

    }

  /**
     * display the private variable {@code}path in the console
     */
    public void printPath() {
        String result="";
        for(RestStop r:path)
        result=result+r.getLable()+" ";
        System.out.println(result.substring(0, result.length()-1));
    }

/**
 * check if hiker has food.
 * @return, true if hiker has food, false otherwise.
 */
    public boolean isFood() {
        return supplyList.contains("food");
    }

    /**
     * remove one food from supplylist
     */
    public void removeFood() {
        supplyList.remove("food");

    }

    /**
     * copy {@code}hiker information of {@code}supplyList and {@code}path to the current Hiker object.
     * @param hiker, hiker object to copy from.
     */
    public void copyContent(Hiker hiker) {
        path.addAll(hiker.path);
        supplyList.addAll(hiker.supplyList);
    }

    /**
     * Check if hiker is able to resolve the obstacles on this RestStop {@code}treeNode.
     * 
     * @param treeNode, RestStop to check
     * @return, tree if hiker is able to resolve the obstacles, false otherwise.
     */
    public boolean updateStatus(RestStop treeNode) {
        path.add(treeNode);
        String[] tmp = treeNode.getSupply();
        int i = 0;
        // update the supply list.
        while (tmp[i] != null) {
            supplyList.add(tmp[i]);
            i++;
        }
       // if (!isFood())
       //     return false;
       // else
       //     removeFood();

        tmp = treeNode.getObsticle();
        i = 0;
        // check of hiker is able to resolve all obstacles in this RestStop.
        while (tmp[i] != null) {
            if (tmp[i].equals("river"))
                if (supplyList.contains("raft")) {
                    supplyList.remove("raft");
                } else {
                    return false;
                }
            if (tmp[i].equals("fallen tree"))
                if (supplyList.contains("axe")) {
                    supplyList.remove("axe");
                } else {
                    return false;
                }
            i++;
        }
        return true;
    }

}
