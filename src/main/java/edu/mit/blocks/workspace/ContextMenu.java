package edu.mit.blocks.workspace;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import edu.mit.blocks.renderable.RenderableBlock;

/**
 * ContextMenu handles all the right-click menus within the Workspace.
 * TODO ria enable customization of what menu items appear, fire events depending
 * on what items are clicked (if we enabled the first feature)
 * 
 * TODO ria still haven't enabled the right click menu for blocks
 */
public class ContextMenu extends PopupMenu implements ActionListener {

    private static final long serialVersionUID = 328149080421L;
    //context menu renderableblocks plus
    //menu items for renderableblock context menu
    private static ContextMenu rndBlockMenu = new ContextMenu();
    private static ContextMenu addCommentMenu = new ContextMenu();
    private static ContextMenu subroutineRemoveCommentMenu = new ContextMenu();
    private static ContextMenu removeCommentMenu = new ContextMenu();
    private static ContextMenu subroutineAddCommentMenu = new ContextMenu();
    
    private final static String ADD_COMMENT_BLOCK = "ADDCOMMENT";
    private static boolean addCommentMenuInit = false;
    private static boolean subroutineAddCommentMenuInit = false;
    private final static String REMOVE_COMMENT_BLOCK = "REMOVECOMMENT";
    private static boolean removeCommentMenuInit = false;
    private static boolean subroutineRemoveCommentMenuInit = false;
    
    private final static String CLONE_BLOCK = "CLONE";	//heqichen
    private final static String CREATE_REF = "CREATE-REF";	//heqichen
    private static MenuItem addCommentItem1 = null;
    private static MenuItem addCommentItem2 = null;
    private static MenuItem removeCommentItem1 = null;
    private static MenuItem removeCommentItem2 = null;
    private static MenuItem cloneItem1 = null;	//heqichen
    private static MenuItem cloneItem2 = null;	//heqichen
    private static MenuItem cloneItem3 = null;	//heqichen
    private static MenuItem cloneItem4 = null;	//heqichen
    private static MenuItem createRefItem1 = null;	//heqichen
    private static MenuItem createRefItem2 = null;	//heqichen
    private final static String BLOCK_SUBROUTINE_NAME = "subroutine";	//heqichen
    //context menu for canvas plus
    //menu items for canvas context menu
    private static ContextMenu canvasMenu = new ContextMenu();
    private static MenuItem arrangeAllBlocks;
    private final static String ARRANGE_ALL_BLOCKS = "ARRANGE_ALL_BLOCKS";
    private static boolean canvasMenuInit = false;
    /** The JComponent that launched the context menu in the first place */
    private static Object activeComponent = null;

    //privatize the constructor
    private ContextMenu() {
    }

    /**
     * Initializes the context menu for adding Comments.
     */
    private static void initAddCommentMenu() {
    	ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
    	
    	addCommentItem1 = new MenuItem(uiMessageBundle.getString("ardublock.ui.add_comment"));
        addCommentItem1.setActionCommand(ADD_COMMENT_BLOCK);
        addCommentItem1.addActionListener(rndBlockMenu);
        addCommentMenu.add(addCommentItem1);
        
    	cloneItem1 = new MenuItem(uiMessageBundle.getString("ardublock.ui.clone"));
    	cloneItem1.setActionCommand(CLONE_BLOCK);
    	cloneItem1.addActionListener(rndBlockMenu);
        addCommentMenu.add(cloneItem1);
        
        addCommentMenuInit = true;
        
    }
    
    /**
     * Initializes the context menu for subroutine adding Comments.
     */
    private static void initSubroutineAddCommentMenu() {
    	ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
    	
    	addCommentItem2 = new MenuItem(uiMessageBundle.getString("ardublock.ui.add_comment"));
        addCommentItem2.setActionCommand(ADD_COMMENT_BLOCK);
        addCommentItem2.addActionListener(rndBlockMenu);
        subroutineAddCommentMenu.add(addCommentItem2);
        
    	cloneItem2 = new MenuItem(uiMessageBundle.getString("ardublock.ui.clone"));
    	cloneItem2.setActionCommand(CLONE_BLOCK);
    	cloneItem2.addActionListener(rndBlockMenu);
    	subroutineAddCommentMenu.add(cloneItem2);
    	
    	createRefItem1 = new MenuItem(uiMessageBundle.getString("ardublock.ui.create_refer"));
    	createRefItem1.setActionCommand(CREATE_REF);
    	createRefItem1.addActionListener(rndBlockMenu);
    	subroutineAddCommentMenu.add(createRefItem1);
        
        subroutineAddCommentMenuInit = true;
        
    }

    /**
     * Initializes the context menu for deleting Comments.
     */
    private static void initRemoveCommentMenu() {
    	ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
    	
        removeCommentItem1 = new MenuItem(uiMessageBundle.getString("ardublock.ui.delete_comment"));
        removeCommentItem1.setActionCommand(REMOVE_COMMENT_BLOCK);
        removeCommentItem1.addActionListener(rndBlockMenu);

        removeCommentMenu.add(removeCommentItem1);
        //rndBlockMenu.add(runBlockItem);
        
    	
    	cloneItem3 = new MenuItem(uiMessageBundle.getString("ardublock.ui.clone"));
    	cloneItem3.setActionCommand(CLONE_BLOCK);
    	cloneItem3.addActionListener(rndBlockMenu);
        removeCommentMenu.add(cloneItem3);
        
        removeCommentMenuInit = true;
    }
    
    /**
     * Initializes the context menu for subroutine deleting Comments.
     */
    private static void initSubroutineRemoveCommentMenu() {
    	ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
    	
        removeCommentItem2 = new MenuItem(uiMessageBundle.getString("ardublock.ui.delete_comment"));
        removeCommentItem2.setActionCommand(REMOVE_COMMENT_BLOCK);
        removeCommentItem2.addActionListener(rndBlockMenu);

        subroutineRemoveCommentMenu.add(removeCommentItem2);
        //rndBlockMenu.add(runBlockItem);
        
    	
    	cloneItem4 = new MenuItem(uiMessageBundle.getString("ardublock.ui.clone"));
    	cloneItem4.setActionCommand(CLONE_BLOCK);
    	cloneItem4.addActionListener(rndBlockMenu);
    	subroutineRemoveCommentMenu.add(cloneItem4);
        
    	createRefItem2 = new MenuItem(uiMessageBundle.getString("ardublock.ui.create_refer"));
    	createRefItem2.setActionCommand(CREATE_REF);
    	createRefItem2.addActionListener(rndBlockMenu);
    	subroutineRemoveCommentMenu.add(createRefItem2);
    	
    	subroutineRemoveCommentMenuInit = true;
    }

    /**
     * Initializes the context menu for the BlockCanvas
     *
     */
    private static void initCanvasMenu() {
    	ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
    	
        arrangeAllBlocks = new MenuItem(uiMessageBundle.getString("ardublock.ui.organize_blocks"));  //TODO some workspaces don't have pages
        arrangeAllBlocks.setActionCommand(ARRANGE_ALL_BLOCKS);
        arrangeAllBlocks.addActionListener(canvasMenu);

        canvasMenu.add(arrangeAllBlocks);

        canvasMenuInit = true;
    }

    /**
     * Returns the right click context menu for the specified JComponent.  If there is 
     * none, returns null.
     * @param o JComponent object seeking context menu
     * @return the right click context menu for the specified JComponent.  If there is 
     * none, returns null.
     */
    /**
     * 
     * modificated: HE Qichen, 2013-06-02
     * 
     */
    public static PopupMenu getContextMenuFor(Object o) {
        if (o instanceof RenderableBlock) {
            if (((RenderableBlock) o).hasComment())
            {
            	if (((RenderableBlock)o).getGenus().equals(BLOCK_SUBROUTINE_NAME))
            	{
            		if (!subroutineRemoveCommentMenuInit)
            		{
            			initSubroutineRemoveCommentMenu();
            		}
            		activeComponent = o;
            		return subroutineRemoveCommentMenu;
            	}
            	else
            	{
	                if (!removeCommentMenuInit)
	                {
	                    initRemoveCommentMenu();
	                }
	                activeComponent = o;
	                return removeCommentMenu;
            	}
            }
            else
            {
            	if (((RenderableBlock)o).getGenus().equals(BLOCK_SUBROUTINE_NAME))
            	{
            		if(!subroutineAddCommentMenuInit)
            		{
            			initSubroutineAddCommentMenu();
            		}
            		activeComponent = o;
            		return subroutineAddCommentMenu;
            	}
            	else
            	{
	                if (!addCommentMenuInit)
	                {
	                    initAddCommentMenu();
	                }
	                activeComponent = o;
	                return addCommentMenu;
            	}
            }
        } else if (o instanceof BlockCanvas) {
            if (!canvasMenuInit) {
                initCanvasMenu();
            }
            activeComponent = o;
            return canvasMenu;
        }
        return null;
    }

    public void actionPerformed(ActionEvent a) {
        if (a.getActionCommand() == ARRANGE_ALL_BLOCKS) {
            //notify the component that launched the context menu in the first place
            if (activeComponent != null && activeComponent instanceof BlockCanvas) {
                ((BlockCanvas) activeComponent).arrangeAllBlocks();
            }
        } else if (a.getActionCommand() == ADD_COMMENT_BLOCK) {
            //notify the renderableblock componenet that lauched the conetxt menu
            if (activeComponent != null && activeComponent instanceof RenderableBlock) {
                ((RenderableBlock) activeComponent).addComment();
            }
        } else if (a.getActionCommand() == REMOVE_COMMENT_BLOCK) {
            //notify the renderableblock componenet that lauched the conetxt menu
            if (activeComponent != null && activeComponent instanceof RenderableBlock) {
                ((RenderableBlock) activeComponent).removeComment();
            }
        } else if (a.getActionCommand() == CLONE_BLOCK) {
        	//notify the renderableblock componenet that lauched the conetxt menu
            if (activeComponent != null && activeComponent instanceof RenderableBlock) {
                ((RenderableBlock) activeComponent).cloneMe();
            }
        } else if (a.getActionCommand() == CREATE_REF){
        	if (activeComponent != null && activeComponent instanceof RenderableBlock){
        		((RenderableBlock) activeComponent).createRefer();
        	}
        }
        	
    }
}
