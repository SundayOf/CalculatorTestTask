class CustomException extends Exception {
    String message;

    CustomException(String m){
        this.message = m;
    }
    public String printMessage(){ return ("throws Exception //т.к.\t" + message); }
}
