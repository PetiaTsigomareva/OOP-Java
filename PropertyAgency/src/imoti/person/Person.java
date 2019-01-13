package imoti.person;

import java.util.Random;

public abstract class Person {
	private String personName;
	private String phoneNumber;

	private static String[] names = { "Niki", "Iva", "Pesho", "Ani", "Buba", "Mara", "Pena", "Gena", "Cura", "Mura" };

	public Person(String personName, String phoneNumber) {

		this.personName = personName;
		this.phoneNumber = phoneNumber;
	}

	public static String getRandomName(String type) {
		int randomIndex = new Random().nextInt(names.length);
		int randomNameIndex = new Random().nextInt(1000);
		return type + "-" + names[randomIndex] + "-" + randomNameIndex;
	}

	public static String getRandomPhoneNumber() {
		int randomPhoneNumber = new Random().nextInt(9000000) + 1000000;
		return "088" + randomPhoneNumber;
	}

	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * @param personName
	 *          the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *          the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [personName=" + personName + ", phoneNumber=" + phoneNumber + handleToString() + "]";
	}

	protected abstract String handleToString();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( personName == null ) ? 0 : personName.hashCode() );
		result = prime * result + ( ( phoneNumber == null ) ? 0 : phoneNumber.hashCode() );
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		if (personName == null) {
			if (other.personName != null) {
				return false;
			}
		} else if (!personName.equals(other.personName)) {
			return false;
		}
		if (phoneNumber == null) {
			if (other.phoneNumber != null) {
				return false;
			}
		} else if (!phoneNumber.equals(other.phoneNumber)) {
			return false;
		}
		return true;
	}

}