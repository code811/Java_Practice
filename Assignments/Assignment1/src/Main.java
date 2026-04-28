import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        int pointX = 0;
        int pointY = 0;

        int area1X = 0;
        int area1Y = 0;
        int area1Width = 0;
        int area1Height = 0;

        int area2X = 0;
        int area2Y = 0;
        int area2Width = 0;
        int area2Height = 0;

        System.out.println("[Intersection Detector]");
        System.out.println();
        do { // Repeats menu options till user quits by entering '7'
            System.out.println("1. Enter Point");
            System.out.println("2. Enter Area 1");
            System.out.println("3. Enter Area 2");
            System.out.println("4. Point-Area Intersection");
            System.out.println("5. Area-Area Intersection");
            System.out.println("6. View info");
            System.out.println("7. Quit");
            System.out.print("Select option: ");
            option = Integer.parseInt(sc.nextLine());

            switch(option) {
                case 1: { // Enter point
                    System.out.print("Enter x-coordinate of Point: ");
                    pointX = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter y-coordinate of Point: ");
                    pointY = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    System.out.println("Point coordinates updated.");
                    System.out.println();
                    break;
                }
                case 2: { // Enter Area 1
                    System.out.print("Enter x-coordinate of Area 1: ");
                    area1X = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter y-coordinate of Area 1: ");
                    area1Y = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter width of Area 1: ");
                    area1Width = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter height of Area 1: ");
                    area1Height = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    System.out.println("Area 1 updated");
                    System.out.println();
                    break;
                }
                case 3: { // Enter Area 2
                    System.out.print("Enter x-coordinate of Area 2: ");
                    area2X = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter y-coordinate of Area 2: ");
                    area2Y = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter width of Area 2: ");
                    area2Width = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter height of Area 2: ");
                    area2Height = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    System.out.println("Area 2 updated");
                    System.out.println();
                    break;
                }
                case 4: { // Point-Area Intersection
                    System.out.print("Use Area 1 or Area 2? ");
                    int areaOption = Integer.parseInt(sc.nextLine());
                    System.out.println();

                    switch(areaOption) { // Determines whether point is either in area 1 or 2
                        case 1: {
                            int topEdge = area1Y;
                            int leftEdge = area1X;
                            int bottomEdge = area1Y + area1Height;
                            int rightEdge = area1X + area1Width;

                            if((pointX < leftEdge) || (pointX > rightEdge) ||
                                    (pointY < topEdge) || (pointY > bottomEdge)) { // Finds if the point's x-coordinate is outside of area 2's width
                                System.out.println("The point is NOT in Area 1.");
                                break;
                            }

                            System.out.println("The point is in Area 1."); // If passed both if-conditions, this must be true
                            break;
                        }
                        case 2: {
                            int topEdge = area2Y;
                            int leftEdge = area2X;
                            int bottomEdge = area2Y + area2Height;
                            int rightEdge = area2X + area2Width;

                            if((pointX < leftEdge) || (pointX > rightEdge) ||
                                    (pointY < topEdge) || (pointY > bottomEdge)) { // Finds if the point's x-coordinate is outside of area 2's width
                                System.out.println("The point is NOT in Area 2.");
                                break;
                            }

                            System.out.println("The point is in Area 2."); // If passed both if-conditions, this must be true
                            break;
                        }
                    }

                    System.out.println();
                    break;
                }
                case 5: { // Area-Area Intersection
                    int topEdge1 = area1Y;
                    int leftEdge1 = area1X;
                    int bottomEdge1 = area1Y + area1Height;
                    int rightEdge1 = area1X + area1Width;

                    int topEdge2 = area2Y;
                    int leftEdge2 = area2X;
                    int bottomEdge2 = area2Y + area2Height;
                    int rightEdge2 = area2X + area2Width;

                    if((leftEdge1 > rightEdge2) || (rightEdge1 < leftEdge2) ||
                            (topEdge1 > bottomEdge2) || (bottomEdge1 < topEdge2)) { // checks to see if rectangle is entirely above, below, left or right of the other
                        System.out.println("The two areas do NOT intersect.");
                        System.out.println();
                        break;

                    }

                    System.out.println("The two areas intersect.");
                    System.out.println();
                    break;
                }
                case 6: { // View Info
                    System.out.println();
                    System.out.println("Point x: " + pointX + ", y: " + pointY);
                    System.out.println("Area 1 x: " + area1X + ", y: " + area1Y + " width: " + area1Width + ", height: " + area1Height);
                    System.out.println("Area 2 x: " + area2X + ", y: " + area2Y + " width: " + area2Width + ", height: " + area2Height);
                    System.out.println();
                    break;
                }
                case 7: { // Quit
                    break;
                }
            }

        } while(option != 7);

        int[][] arr = {{1, 2}, {3, 4}};

        System.out.println("Shutting Off...");
        sc.close();
    }
}
