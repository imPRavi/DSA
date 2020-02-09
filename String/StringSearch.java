class StringSearch {
	public void KMP(String str, String pat){
		int lenStr = str.length();
		int lenPat = pat.length();
		// If Pattern length is greater than String length then we can't find
		if(lenStr < lenPat)
			return;
		// Prepare the lps table
		int[] lps = createLPS(pat);
		
		int i = 0, j = 0;
		while(i < lenStr) {
			if(str.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			    
			    // Check if pattern found
				if(j == lenPat) {
					System.out.println("Pattern found @ "+ (i-lenPat));
					j = lps[j-1];
				}
			}
			// This step is tricky but similer to else in createLPS()
			else {
				if(j != 0)
					j = lps[j-1];
				else 
					i++;
			}
		}
		return;
	}
	
	// This is helper function to process the pattern
	int[] createLPS(String pat){
		int lenPat = pat.length();
		int[] lps = new int[lenPat];
		lps[0] = 0;
		
		int j = 1, len = 0;
		while(j < lenPat){
			if(pat.charAt(j) == pat.charAt(len)) {
				len++;
				lps[j] = len;
				j++;
			}
			else {
				if( len != 0 ) 
					len = lps[len-1];
				else {
					lps[j] = 0;
					j++;
				}
			}
		}
		return lps;
	}
	
	public static void main(String[] args) {
		String str = "abcabcabcdeabcvdabcdf";
		String pat = "abcd";
		new MyClass().KMP(str, pat);
	}
}