package jproksch;

import jproksch.Gui;

public class Main {

    public static void main(String[] args) {
        Rechtschreibtrainer rechtschreibtrainer = new Rechtschreibtrainer();
        rechtschreibtrainer.getInformation(rechtschreibtrainer,"C:\\Users\\julia\\IdeaProjects\\Worttrainer_Reloaded\\src\\main\\java\\jproksch\\info");
        Gui gui = new Gui();
        gui.createAndShowGUI(rechtschreibtrainer);
    }
}
