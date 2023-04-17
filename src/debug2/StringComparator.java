package debug2;

public class StringComparator implements Comparator<String> {

    @Override
    public boolean compare(String a, String b) {
        if(a.length() < b.length()){
            return true;
        }else {
            return a.compareToIgnoreCase(b) > 0;
        }
    }

}
