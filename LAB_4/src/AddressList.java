
public class AddressList {
	public static int countAddressWithCity(Address[] AddressList,String city){
		if(AddressList.length == 0 || city == null || AddressList == null)return -1;
		int count = 0;
		for(Address i : AddressList){
			if(i.getCity()==city)
				count++;
		}
		return count;

	}

public static Address[] getAddreessWithCity(Address[] addresslist,String city){
	if(addresslist.length == 0 || city == null || addresslist == null)return null;
	int count=0;
	Address[] cityList = new Address[addresslist.length];
	for(Address i : addresslist){
		if(i.getCity()==city){
			cityList[0] = i;
			count++;
		}
	}
	return cityList;
}
}