/*
 * ApplicationListener.java
 */

import java.util.EventListener;

/**
 *
 * @author PleweJ
 */
public interface ApplicationListener extends EventListener 
{
    void applicationDidInit();
    void applicationExiting();
    boolean canApplicationExit();
}
