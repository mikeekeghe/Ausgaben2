/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.category;

import beans.Category;
import bl.AusgabenBl;
import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Bernhard
 */
public class CategoryTreeModel  implements TreeModel{
    
    private ArrayList<Category> categories;

    public CategoryTreeModel(ArrayList<Category> categories) {
        this.categories = categories;
    }
    
    

    @Override
    public Object getRoot() {
        for(Category cat: categories){
            if(cat.getPriorCategory() == null)
                return cat;
        }
        
        return null;
    }

    @Override
    public Object getChild(Object parent, int index) {
         Category catParent = (Category) parent;
         if(index < catParent.getSubCategories().size())
             return catParent.getSubCategories().get(index);
         else
             return null;
    }

    @Override
    public int getChildCount(Object parent) {
        Category catParent = (Category) parent;
        return catParent.getSubCategories().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        if(getChildCount(node) == 0)
            return true;
        
        return false;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Category catParent = (Category) parent;
        Category catChild = (Category) child;
        
        return catParent.getSubCategories().indexOf(catChild);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        
    }
    
    
    
}
