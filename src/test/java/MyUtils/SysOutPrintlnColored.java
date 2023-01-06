package MyUtils;

// Coloured System.out.println outputs to more easily see the messages on the console. 
public class SysOutPrintlnColored {
        // Reference https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println#:~:text=If%20the%20console%20support%20(e.g.,for%20one%20color%20and%20System.
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        
        public void purplePrint(String stringToPrint){
            System.out.println(ANSI_PURPLE + stringToPrint + ANSI_RESET);
        }
}
