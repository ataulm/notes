package com.ataulm.notes;

class Marks2DArrayTrimmer {

    public static Mark[][] trim(Mark[][] array) {
        if (columnIsEmpty(array, 0)) {
            return trim(deleteColumn(array, 0));
        } else if (columnIsEmpty(array, array[0].length - 1)) {
            return trim(deleteColumn(array, array[0].length - 1));
        } else {
            return array;
        }
    }

    private static boolean columnIsEmpty(Mark[][] array, int column) {
        for (int row = 0; row < array.length; row++) {
            if (array[row][column] != null) {
                return false;
            }
        }
        return true;
    }

    private static Mark[][] deleteColumn(Mark[][] array, int columnToRemove) {
        Mark[][] modified = new Mark[array.length][array[0].length - 1];
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                if (column == columnToRemove) {
                    continue;
                }
                int writeColumnIndex = column < columnToRemove ? column : column - 1;
                modified[row][writeColumnIndex] = array[row][column];
            }
        }
        return modified;
    }

}
