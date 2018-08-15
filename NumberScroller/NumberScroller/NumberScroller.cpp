#include <iostream>
#include <Windows.h>

int currentKey = 1;
HHOOK mouseHook;
INPUT input;

void initKeyboard()
{
	//Set up a generic keyboard event.
	input.type = INPUT_KEYBOARD;
	input.ki.wScan = 0;
	input.ki.time = 0;
	input.ki.dwExtraInfo = 0;
}

void typeKey()
{
	//Keycode for '0'
	int zeroKey = 0x30;

	//Determine virtual keycode
	switch (currentKey)
	{
		case 10:
			input.ki.wVk = zeroKey;
			break;
		default:
			input.ki.wVk = zeroKey + currentKey;
			break;
	}
	
	//Press key
	input.ki.dwFlags = 0;
	SendInput(1, &input, sizeof(INPUT));

	//Release key
	input.ki.dwFlags = KEYEVENTF_KEYUP;
	SendInput(1, &input, sizeof(INPUT));
}

LRESULT __stdcall MouseHookCallback(int nCode, WPARAM wParam, LPARAM lParam)
{
	if (nCode >= 0)
	{
		if (wParam == WM_MOUSEWHEEL)
		{
			MSLLHOOKSTRUCT* pMouseStruct = (MSLLHOOKSTRUCT*)lParam;

			//Determine wheel direction
			(short)HIWORD(pMouseStruct->mouseData) > 0 ? currentKey++ : currentKey--;

			//Fit values to range
			if (currentKey < 1)
				currentKey = 10;
			else if (currentKey > 10)
				currentKey = 1;

			//Send input
			typeKey();
		}
	}

	return CallNextHookEx(mouseHook, nCode, wParam, lParam);
}

int WINAPI main()
{
	MSG msg;
	SetWindowsHookEx(WH_MOUSE_LL, MouseHookCallback, NULL, 0);

	initKeyboard();

	while (GetMessage(&msg, NULL, 0, 0))
	{
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}

	UnhookWindowsHookEx(mouseHook);

	return 0;
}