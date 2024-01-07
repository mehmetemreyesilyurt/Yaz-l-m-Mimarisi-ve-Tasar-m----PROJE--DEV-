public class BorrowDatabase extends Observable {
    private String book;

    public BorrowDatabase(String book){
        this.book=book;
    }

    public String getBook(){
        return book;
    }
    public void borrowProcess(String firstTakenBook,String lastTakenBook){
        if(firstTakenBook.equals("-") & lastTakenBook.equals(book)){
            obsUpdate();
        }
    }
}