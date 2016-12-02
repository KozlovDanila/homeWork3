public class NegativeValue implements Predicate {
    @Override
    public boolean apply(int number) {
        if (number < 0) {
            return true;
        }
        return false;
    }
}

