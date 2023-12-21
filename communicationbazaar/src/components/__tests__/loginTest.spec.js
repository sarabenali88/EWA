import {mount} from "@vue/test-utils";
import i18n from "@/i18n";
import {reactive} from "vue";
import SignInComponent from "@/components/SignInComponent";
import {AccountsAdaptor} from "@/services/AccountsAdaptor";

let wrapper

const mockAccounts = [
    {
        personalNumber: 1,
        password: 'Welkom01.',
        name: 'jasper',
        email: 'ja@ja.nl',
        role: 'admin',
        location: 'Amsterdam',
        loggedIn: false
    },
    {
        personalNumber: 2,
        password: 'halloP9!.',
        name: 'Pieter',
        email: 'nee@nee.nl',
        role: 'admin',
        location: 'Amsterdam',
        loggedIn: false
    },
];

beforeEach(async function () {

    const accountsService = new AccountsAdaptor('http://localhost:8086/api');

    jest.spyOn(accountsService, "asyncFindAll").mockResolvedValue(mockAccounts);

    // Mocking asyncFindById method
    jest.spyOn(accountsService, "asyncFindById").mockImplementation(async (personalNumber) => {
        const foundAccount = mockAccounts.find(account => account.personalNumber === personalNumber);
        return foundAccount ? foundAccount : null;
    });

    jest.spyOn(accountsService, "asyncSave").mockImplementation(async (account) => {
        if (account.personalNumber === 0) {
            account.personalNumber = mockAccounts.length+1;
            return account;
        } else {
            mockAccounts.push(account);
            return account;
        }
    });

    jest.spyOn(accountsService, "asyncDeleteById").mockResolvedValue({ success: true });

    wrapper = mount(SignInComponent, {
        global: {
            provide: {
                "accountsService": reactive(accountsService)
            },
            plugins: [i18n]
        }
    });
});

it('should render properly', function () {

    expect(wrapper.element.children.length,
        `main page starting with ${wrapper.element.tagname} has no child elements`)
        .toBeGreaterThan(0);
});

it('can log in', async function () {
    const button = wrapper.findAll('button').find(b => b.text() === 'signIn.logInButton')

    expect(button.exists(),
        `Cannot find button with id loginButton`)
        .toBe(true);
    expect(button.element.disabled,
        `button with id loginButton has been disabled`)
        .toBeFalsy();

    await button.trigger('click');
});
