/*
 *  ???.cpp
 *  Created on: 2012-11-19
 *  Author:???
 */
int f(int a, int b) {//?????a?b????????????
	if (a == b)//?a??b????a??b?
		return a;
	if (a > b) {//?a??b,???a??????b????
		return f(a % 2 == 0 ? a / 2 : (a - 1) / 2, b);
	} else {//?b??a,???b??????b????
		return f(a, b % 2 == 0 ? b / 2 : (b - 1) / 2);
	}
}
int main() {
	int a, b;
	cin >> a >> b;//?????
	cout << f(a, b) << endl;//????????
	return 0;
}