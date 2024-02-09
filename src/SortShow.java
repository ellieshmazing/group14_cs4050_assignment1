/**
 * @author Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel {


    // An array to hold the lines_lengths to be sorted
    public int[] lines_lengths;
    //The amount of lines needed
    public final int total_number_of_lines = 256;
    // An array to holds the scrambled lines_lengths
    public int[] scramble_lines;
    //A temp Array that is used later for sorts
    public int[] tempArray;

    //the default constructor for the SortShow class
    public SortShow() {
        //assigning the size for the lines_lengths below
        lines_lengths = new int[total_number_of_lines];
        for (int i = 0; i < total_number_of_lines; i++)
            lines_lengths[i] = i + 5;

    }


    //A method that scrambles the lines
    public void scramble_the_lines() {
        //A random generator
        Random num = new Random();
        //Randomly switching the lines
        for (int i = 0; i < total_number_of_lines; i++) {
            //getting a random number using the nextInt method (a number between 0 to i + 1)
            int j = num.nextInt(i + 1);
            //swapping The element at i and j
            swap(i, j);
        }
        //assigning the size for the scramble_lines below
        scramble_lines = new int[total_number_of_lines];
        //copying the now scrambled lines_lengths array into the scramble_lines array
        //to store for reuse for other sort methods
        //so that all sort methods will use the same scrambled lines for fair comparison
        for (int i = 0; i < total_number_of_lines; i++) {
            scramble_lines[i] = lines_lengths[i];
        }
        //Drawing the now scrambled lines_lengths
        paintComponent(this.getGraphics());
    }

    //Swapping method that swaps two elements in the lines_lengths array
    public void swap(int i, int j) {
        //storing the i element in lines_lengths in temp
        int temp = lines_lengths[i];
        //giving i element in lines_lengths the value of j element in lines_lengths
        lines_lengths[i] = lines_lengths[j];
        //giving j element in lines_lengths the value of temp
        lines_lengths[j] = temp;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //The bubbleSort method implemented by Ellie
    public void BubbleSort() {
        //getting the date and time when the bubble merge sort starts
        Calendar start = Calendar.getInstance();
        //Sorting loop, iterating once to find current largest unsorted element
        for (int i = 0; i < total_number_of_lines - 1; i++) {
            //Rerenders graphics every loop
            paintComponent(this.getGraphics());
            //Inner loop to swap elements until largest unsorted is at beginning of sorted area
            for (int j = 0; j < total_number_of_lines - 1 - i; j++) {
                //Compares current element with next element
                if (lines_lengths[j] > lines_lengths[j + 1]) {
                    //If current element is larger, then swap
                    swap(j, j + 1);
                }
            }
        }

        Calendar end = Calendar.getInstance();
        //getting the time it took for the iterative merge sort to execute
        //subtracting the end time with the start time
        SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
    }
    ///////////////////////////////////////////////////////////////////////////////////

    //The selectionSort method
    //Implemented by Atul Krishnadas
    public void SelectionSort() {
        // Record the start time of the selection sort
        Calendar start = Calendar.getInstance();

        for (int i = 0; i < total_number_of_lines - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i + 1; j < total_number_of_lines; j++) {
                if (lines_lengths[j] < lines_lengths[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            if (minIndex != i) {
                swap(i, minIndex);
                paintComponent(this.getGraphics());
            }
        }

        Calendar end = Calendar.getInstance();
        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    //this method gets the smallest element in the array of lines_lengths
    public int getIndexOfSmallest(int first, int last) {

        //You need to complete this part.

        return 1; //modify this line
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //The Insertion Sort Method
    //Implemented by Atul Krishnadas
    public void InsertionSort() {
        // Record the start time of the insertion sort
        Calendar start = Calendar.getInstance();
        // Start from the second element (index 1)
        for (int i = 1; i < total_number_of_lines; i++) {
            int current = lines_lengths[i];
            int j = i - 1;
            // Move elements of lines_lengths[0..i-1], that are greater than current, to one position ahead
            while (j >= 0 && lines_lengths[j] > current) {
                lines_lengths[j + 1] = lines_lengths[j];
                j = j - 1;

//                paintComponent(this.getGraphics());
            }
            lines_lengths[j + 1] = current;

             paintComponent(this.getGraphics());
        }

        Calendar end = Calendar.getInstance();
        SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //The Shell Sort Method
    //Implemented by Olothando Masiza
    public void ShellSort() {
        //getting the date and time when the recursive merge sort starts
        Calendar start = Calendar.getInstance();

        //assigning the size for the tempArray below
        int n = lines_lengths.length;

        //gap calculation
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                //rendering the sorting over time with the GUI
                paintComponent(this.getGraphics());


                int temp = lines_lengths[i];

                int j;
                for (j = i; j >= gap && lines_lengths[j - gap] > temp; j -= gap) {
                    lines_lengths[j] = lines_lengths[j - gap];
                }
                lines_lengths[j] = temp;
            }
        }

        Calendar end = Calendar.getInstance();
        //getting the time it took for the iterative merge sort to execute
        //subtracting the end time with the start time
        SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();
    }
    ///////////////////////////////////////////////////////////////////////////////////

    //Implemented by Olothando Masiza
    public void R_MergeSort() {
        //getting the date and time when the recursive merge sort starts
        Calendar start = Calendar.getInstance();
        //assigning the size for the tempArray below

        R_MergeSort(0, total_number_of_lines - 1);

        Calendar end = Calendar.getInstance();
        //getting the time it took for the iterative merge sort to execute
        //subtracting the end time with the start time
        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();

    }

    //recursive merge sort method
    public void R_MergeSort(int first, int last) {
//        delay(10);
        //base case to stop the recusion
        if (first < last) {

            //Finding the mid without causing integer overflow
            int mid = first + (last - first) / 2;

            //Recursive merge sort
            R_MergeSort(first, mid);
            R_MergeSort(mid + 1, last);
            //merging of the two sub arrays
            R_Merge(first, mid, last);
            printComponent(this.getGraphics());

        }
    }


        //recursive merge sort method
        public void R_Merge ( int first, int mid, int last){


            //size of the temp arrays used for merging
            int n1 = mid - first + 1;
            int n2 = last - mid;

            //temp arrays created
            int left[] = new int[n1];
            int right[] = new int[n2];

            //Copying data into sub array left
            for (int i = 0; i < n1; i++) {

                left[i] = lines_lengths[first + i];
            }
            //Copying data into sub arry right
            for (int j = 0; j < n2; j++) {
                //I am using + 1 because the left includes the middle and I want to start after the middle
                right[j] = lines_lengths[mid + j + 1];
            }

            //Indicies to merge the different sub-arrays
            //Set to zero to begin of each sub array respectively
            int i = 0, j = 0;


            //Index for the array that values will be merged into
            int k = first;

            //populating the left and right sub arrays
            while (i < n1 && j < n2) {

                if (left[i] < right[j]) {
                    lines_lengths[k] = left[i];
                    i++;
                } else {
                    lines_lengths[k] = right[j];
                    j++;
                }
                k++;
            }
            //If needed put the remainder of the elements of the subarray left array into the merge array
            while (i < n1) {
                lines_lengths[k] = left[i];
                i++;
                k++;
            }
            //If needed put the remainder of the elements of the subarray right array into the merge array
            while (j < n2) {
                lines_lengths[k] = right[j];
                j++;
                k++;
            }
        }

        //////////////////////////////////////////////////////////////////////////////////////////

        //iterative merge sort method
        public void I_MergeSort ()
        {
            //getting the date and time when the iterative merge sort starts
            Calendar start = Calendar.getInstance();
            //assigning the size for the tempArray below
            tempArray = new int[total_number_of_lines];
            //saving the value of total_number_of_lines
            int beginLeftovers = total_number_of_lines;


            for (int segmentLength = 1; segmentLength <= total_number_of_lines / 2; segmentLength = 2 * segmentLength) {
                beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
                int endSegment = beginLeftovers + segmentLength - 1;
                if (endSegment < total_number_of_lines - 1) {
                    I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
                }
            }

            // merge the sorted leftovers with the rest of the sorted array
            if (beginLeftovers < total_number_of_lines) {
                I_Merge(0, beginLeftovers - 1, total_number_of_lines - 1);
            }
            //getting the date and time when the iterative merge sort ends
            Calendar end = Calendar.getInstance();
            //getting the time it took for the iterative merge sort to execute
            //subtracting the end time with the start time
            SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
        }

        // Merges segments pairs (certain length) within an array
        public int I_MergeSegmentPairs ( int l, int segmentLength)
        {
            //The length of the two merged segments

            //You suppose  to complete this part (Given).
            int mergedPairLength = 2 * segmentLength;
            int numberOfPairs = l / mergedPairLength;

            int beginSegment1 = 0;
            for (int count = 1; count <= numberOfPairs; count++) {
                int endSegment1 = beginSegment1 + segmentLength - 1;

                int beginSegment2 = endSegment1 + 1;
                int endSegment2 = beginSegment2 + segmentLength - 1;
                I_Merge(beginSegment1, endSegment1, endSegment2);

                beginSegment1 = endSegment2 + 1;
                //redrawing the lines_lengths
                paintComponent(this.getGraphics());
                //Causing a delay for 10ms
//                delay(10);
            }
            // Returns index of last merged pair
            return beginSegment1;
            //return 1;//modify this line
        }

        public void I_Merge ( int first, int mid, int last)
        {
            //You suppose  to complete this part (Given).
            // Two adjacent sub-arrays
            int beginHalf1 = first;
            int endHalf1 = mid;
            int beginHalf2 = mid + 1;
            int endHalf2 = last;

            // While both sub-arrays are not empty, copy the
            // smaller item into the temporary array
            int index = beginHalf1; // Next available location in tempArray
            for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++) {
                // Invariant: tempArray[beginHalf1..index-1] is in order
                if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2]) {
                    tempArray[index] = lines_lengths[beginHalf1];
                    beginHalf1++;
                } else {
                    tempArray[index] = lines_lengths[beginHalf2];
                    beginHalf2++;
                }
            }
            //redrawing the lines_lengths
            paintComponent(this.getGraphics());

            // Finish off the nonempty sub-array

            // Finish off the first sub-array, if necessary
            for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
                // Invariant: tempArray[beginHalf1..index-1] is in order
                tempArray[index] = lines_lengths[beginHalf1];

            // Finish off the second sub-array, if necessary
            for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
                // Invariant: tempa[beginHalf1..index-1] is in order
                tempArray[index] = lines_lengths[beginHalf2];

            // Copy the result back into the original array
            for (index = first; index <= last; index++)
                lines_lengths[index] = tempArray[index];
        }

        //////////////////////////////////////////////////////////////////////
        //The Quick Sort Method
        //Works in-place, but recursively
        //Features delays to allow rendering time, but obviously would be quicker without
        //Implemented by Ellie
        public void QuickSort () {
            //getting the date and time when the quick sort starts
            Calendar start = Calendar.getInstance();
            //Initial call of QuickSort
            QuickSort(0, total_number_of_lines - 1);

            Calendar end = Calendar.getInstance();
            //getting the time it took for the quick sort to execute
            //subtracting the end time with the start time
            SortGUI.quickTime = end.getTime().getTime() - start.getTime().getTime();
        }

        //Recursive QuickSort method, takes first and last indices as parameters
        public void QuickSort ( int first, int last){
            //Rerenders graphics every time function is called
            paintComponent(this.getGraphics());
//            delay(10);

            //Terminates when base case of array size 1 met
            if (first < last) {
                //Sorts elements around pivot
                int pivotIndex = partition(first, last);

                //Calls QuickSort on segment less than pivot
                QuickSort(first, pivotIndex - 1);
                //Calls QuickSort on segement greater than pivot
                QuickSort(pivotIndex + 1, last);
            }
        }

        //Partition method to sort elements into segments around pivot
        public int partition ( int first, int last){
            //Set pivot as last value in segment
            int pivot = lines_lengths[last];

            //Index marking end of segment of elements smaller than pivot
            int lessSeg = first - 1;

            //Sort segment around pivot value
            for (int i = first; i <= last; i++) {
                //Compare element to pivot
                if (lines_lengths[i] < pivot) {
                    //If smaller, place in lesser Segment
                    lessSeg++;
                    swap(lessSeg, i);
                }
            }

            //Place pivot after lesser segment
            swap(lessSeg + 1, last);
            return lessSeg + 1;
        }
        ///////////////////////////////////////////////////////////////////////////////////

        //This method resets the window to the scrambled lines display
        public void reset () {
            if (scramble_lines != null) {
                //copying the old scrambled lines into lines_lengths
                for (int i = 0; i < total_number_of_lines; i++) {
                    lines_lengths[i] = scramble_lines[i];
                }
                //Drawing the now scrambled lines_lengths
                paintComponent(this.getGraphics());
            }
        }


        //This method colours the lines and prints the lines
        public void paintComponent (Graphics g){
            super.paintComponent(g);
            //A loop to assign a colour to each line
            for (int i = 0; i < total_number_of_lines; i++) {
                //using eight colours for the lines
                if (i % 8 == 0) {
                    g.setColor(Color.green);
                } else if (i % 8 == 1) {
                    g.setColor(Color.blue);
                } else if (i % 8 == 2) {
                    g.setColor(Color.yellow);
                } else if (i % 8 == 3) {
                    g.setColor(Color.red);
                } else if (i % 8 == 4) {
                    g.setColor(Color.black);
                } else if (i % 8 == 5) {
                    g.setColor(Color.orange);
                } else if (i % 8 == 6) {
                    g.setColor(Color.magenta);
                } else
                    g.setColor(Color.gray);

                //Drawing the lines using the x and y-components
                g.drawLine(4 * i + 25, 300, 4 * i + 25, 300 - lines_lengths[i]);
            }

        }

        //A delay method that pauses the execution for the milliseconds time given as a parameter
        public void delay ( int time){
            try {
                Thread.sleep(time);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

    }

