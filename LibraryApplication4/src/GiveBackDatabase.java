public class GiveBackDatabase extends Observable {
    private String book;

    public GiveBackDatabase(String book){
        this.book=book;
    }

    public String getBook(){
        return book;
    }
    public void givebackProcess(String firstTakenBook,String lastTakenBook){
        if(firstTakenBook.equals(book) & lastTakenBook.equals("-")){
            obsUpdate();
        }
    }
}