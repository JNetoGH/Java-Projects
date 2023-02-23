public final class GeneralResources {

    public static final int WIDTH = 450;
    public static final int HEIGHT = 500;
    public static final int TOT_BUTTONS_PER_LINE = 5;
    public static final int BUTTONS_GAP = 5;
    public static final double BUTTONS_WIDTH = WIDTH / TOT_BUTTONS_PER_LINE-8; //tira um pouquin q sempre fica um pouquinho maior
    public static final double BUTTONS_HEIGHT = HEIGHT/6-17;
    public static final javafx.scene.text.Font BUTTONS_FONT = new javafx.scene.text.Font("Consola", 20);

    public static int fibonacci(int requiredTerm) {
        int currentTerm = 0, nextTerm = 1, sum = 0;
        if (requiredTerm == 2) return 1;
        for(int i = 0; i < requiredTerm-2; ++i) {
            sum = currentTerm + nextTerm;
            currentTerm = nextTerm;
            nextTerm = sum;
        }
        return sum;
    }

    // se for inteiro corta os zeros extras, se n retorna como era antes msm
    public static String checkIfIsIntAndCutZerosOff(double num) {
        String numAsString = String.valueOf(num);
        int pointIndex = numAsString.indexOf('.');
        boolean isInt = true;

        for (int i = pointIndex+1; i < numAsString.length(); i++) {
            if (numAsString.charAt(i) != '0') {
                isInt = false;
                break;
            }
       }

        if (isInt) return numAsString.substring(0, pointIndex);
        else return String.valueOf(num);
    }

}
