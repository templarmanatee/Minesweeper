public class Cell {
    private String contents = "-";
    private boolean hasBomb = false;

    public String getContents() {
        return contents;
    }

    public void setContents(String bombSniffer) {
        contents = bombSniffer;
    }

    public boolean getHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean plantMine) {
        hasBomb = plantMine;
    }
}
