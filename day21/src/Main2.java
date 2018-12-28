public class Main2 {

	static int reg0, reg1, reg2, reg3, reg4, reg5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		reg1 = 123;
		reg1 &= 456;

		if (reg1 != 72) {
			return;
		}

		//code line 5
		reg1 = 0;
		reg2 = reg1 | 65536;
		reg1 = 8725355;
		
		//code line 8
		reg5 = reg2 & 255;
		reg1 += reg5;
		reg1 &= 16777215;
		reg1 *= 65899;
		reg1 &= 16777215;

		if (256 > reg2) {
			reg5 = 1;
			reg4 = 27;
		} else {
			reg5 = 0;
			
			//code line 18
			reg3 = reg5 + 1;
			reg3 *= 256;

			if (reg3 > reg2) {
				reg3 = 1;
				reg4 = 25;
			} else {
				reg3 = 0;
				reg5 += 1;
				reg4 = 17;
				
				//code line 26
				reg2 = reg5;
				reg4 = 7;

				if (reg1 == reg0) {
					reg5 = 1;
					return;
				} else {
					reg5 = 0;
					//GOTO START
					reg4 = 5;
				}
			}
		}
		System.out.println("end of code");
	}
	
//end of class
}

/*
seti 123 0 1
bani 1 456 1
eqri 1 72 1
addr 1 4 4
seti 0 0 4

seti 0 7 1
bori 1 65536 2
seti 8725355 6 1
bani 2 255 5
addr 1 5 1
bani 1 16777215 1

muli 1 65899 1
bani 1 16777215 1

gtir 256 2 5
addr 5 4 4
addi 4 1 4
seti 27 8 4

seti 0 0 5
addi 5 1 3
muli 3 256 3

gtrr 3 2 3
addr 3 4 4
addi 4 1 4
seti 25 1 4

addi 5 1 5
seti 17 9 4
setr 5 1 2
seti 7 6 4

eqrr 1 0 5
addr 5 4 4
seti 5 7 4
*/