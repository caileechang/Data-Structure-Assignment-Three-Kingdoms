package DSAssignment.BasicFeatures;

import java.util.ArrayList;
import java.util.List;

public class Q1 {

    public static void printTreeDiagram(Node node, String prefix, boolean isTail) {
        if (node.armyType != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.name);
        }

        List<Node> children = node.children;
        for (int i = 0; i < children.size() - 1; i++) {
            Node child = children.get(i);
            printTreeDiagram(child, prefix + (isTail ? "    " : "│   "), false);
        }

        if (children.size() > 0) {
            Node lastChild = children.get(children.size() - 1);
            printTreeDiagram(lastChild, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    public static void main(String[] args) {
        Node sunQuan = new Node("Sun Quan", "Cavalry", 96, 98, 72, 77, 95);

        Node zhouYu = new Node("Zhou Yu", "Cavalry", 80, 86, 97, 80, 90);
        Node zhangZhao = new Node("Zhang Zhao", "Archer", 22, 80, 89, 99, 60);

        Node emperor = new Node("Emperor Sun Quan", null, 0, 0, 0, 0, 0);
        emperor.addChild(sunQuan);

        Node militaryDepartment = new Node("Military Department", null, 0, 0, 0, 0, 0);

        Node managementDepartment = new Node("Management Department", null, 0, 0, 0, 0, 0);

        // Add generals to the generals node and assign them to the respective departments
        Node xuSheng = new Node("Xu Sheng", "Archer", 90, 78, 72, 40, 94);
        Node zhuGeJin = new Node("Zhu Ge Jin", "Archer", 63, 61, 88, 82, 71);
        Node luSu = new Node("Lu Su", "Infantry", 43, 87, 84, 88, 0);
        Node taiShiCi = new Node("Tai Shi Ci", "Cavalry", 96, 81, 43, 33, 97);
        Node xiaoQiao = new Node("Xiao Qiao", "Infantry", 42, 52, 89, 77, 34);
        Node daQiao = new Node("Da Qiao", "Cavalry", 39, 62, 90, 62, 41);
        Node zhouTai = new Node("Zhou Tai", "Infantry", 92, 89, 72, 43, 99);
        Node ganNing = new Node("Gan Ning", "Archer", 98, 92, 45, 23, 97);
        Node luMeng = new Node("Lu Meng", "Cavalry", 70, 77, 93, 83, 88);
        Node huangGai = new Node("Huang Gai", "Infantry", 83, 98, 72, 42, 89);

        assignToDepartment(xuSheng, managementDepartment, militaryDepartment);
        assignToDepartment(zhuGeJin, managementDepartment, militaryDepartment);
        assignToDepartment(luSu, managementDepartment, militaryDepartment);
        assignToDepartment(taiShiCi, managementDepartment, militaryDepartment);
        assignToDepartment(xiaoQiao, managementDepartment, militaryDepartment);
        assignToDepartment(daQiao, managementDepartment, militaryDepartment);
        assignToDepartment(zhouTai, managementDepartment, militaryDepartment);
        assignToDepartment(ganNing, managementDepartment, militaryDepartment);
        assignToDepartment(luMeng, managementDepartment, militaryDepartment);
        assignToDepartment(huangGai, managementDepartment, militaryDepartment);

        // Connect nodes to form the hierarchy
        sunQuan.addChild(zhouYu);
        sunQuan.addChild(zhangZhao);
        zhouYu.addChild(managementDepartment);
        zhangZhao.addChild(militaryDepartment);

        printTreeDiagram(sunQuan, "", true);
//        printHierarchy(emperor, 0);

    }

    public static void printHierarchy(Node node, int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("    ");
        }

        if (level == 0) {
            System.out.println(indent + "- Emperor: " + node.name);
        } else {
            System.out.println(indent + "- " + node.name);
        }

        for (Node child : node.children) {
            printHierarchy(child, level + 1);
        }
    }

    public static void assignToDepartment(Node general, Node managementDepartment, Node militaryDepartment) {
        if (general.department.equals("Management Department")) {
            managementDepartment.addChild(general);
        } else {
            militaryDepartment.addChild(general);
        }
    }

    static class Node {

        String name;
        String armyType;
        int strength;
        int leadership;
        int intelligence;
        int politic;
        int hitPoint;
        List<Node> children;
        private String department;

        public Node(String name, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
            if (name.equals("Zhou Yu")) {
                this.name = name + " (Military Chief)";
            } else if (name.equals("Zhang Zhao")) {
                this.name = name + " (Management Chief)";
            } else if (name.equals("Sun Quan")) {
                this.name = name + " (Emperor)";
            } else {
                this.name = name;
            }
            this.armyType = armyType;
            this.strength = strength;
            this.leadership = leadership;
            this.intelligence = intelligence;
            this.politic = politic;
            this.hitPoint = hitPoint;
            this.children = new ArrayList<>();

            if (strength > intelligence) {
                this.department = "Military Department";
            } else {
                this.department = "Management Department";
            }
        }

        public void addChild(Node child) {
            children.add(child);
        }
    }

}
