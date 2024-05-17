public class DinnerFullCource {
   
	private Dish[] list = new Dish[5];// [0]-[4]の計5個

    public static void main(String[] args) {

		DinnerFullCource fullcourse = new DinnerFullCource();
		fullcourse.eatAll();
	}


    DinnerFullCource(){
    list[0] = new Dish();
    list[0].setName("ขนมจีน");
    list[0].setValune(60);
    list[1] = new Dish();
    list[1].setName("ข้าวเกรียบปากหม้อ");
    list[1].setValune(80);
    list[2] = new Dish();
    list[2].setName("ไข่พะโล้");
    list[2].setValune(50);
    list[3] = new Dish();
    list[3].setName("วุ้นกะทิ");
    list[3].setValune(40);
    list[4] = new Dish();
    list[4].setName("ไอติมหลอด");
    list[4].setValune(50);
    }
    void eatAll() {
		String str = "";

		for (Dish element : list) {
			str += element.getName() + "=" + element.getValune() + "：";
		}

		System.out.println("今日の晩御飯コースは" + str + "です。");
	}

}

