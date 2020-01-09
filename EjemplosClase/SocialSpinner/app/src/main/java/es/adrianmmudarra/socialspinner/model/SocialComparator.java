package es.adrianmmudarra.socialspinner.model;

import java.util.Comparator;

public class SocialComparator implements Comparator<Social> {

    @Override
    public int compare(Social o1, Social o2) {
        if (o1.getTotalUser() < o2.getTotalUser()) {
            return 1;
        }
        if (o1.getTotalUser() > o2.getTotalUser()) {
            return -1;
        }
        if (o1.getTotalUser() == o2.getTotalUser()) {
            return Integer.compare(o1.getDolares(), o2.getDolares());
        }
        return -1;
    }
}
