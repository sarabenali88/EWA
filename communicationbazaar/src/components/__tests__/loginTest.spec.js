import {mount} from "@vue/test-utils";
import i18n from "@/i18n";
import {reactive} from "vue";
import SignInComponent from "@/components/SignInComponent";
import {AccountsAdaptor} from "@/services/AccountsAdaptor";
import {createMemoryHistory, createRouter} from "vue-router";
import WelcomeComponent from "@/components/WelcomeComponent";

let wrapper

const mockRoutes = [
        {
            path: '/',
            component: WelcomeComponent
        },
];

const mockAccounts = [
    {
        personalNumber: "1",
        password: 'Welkom01.',
        name: 'jasper',
        email: 'ja@ja.nl',
        role: 'admin',
        location: 'Amsterdam',
        loggedIn: false
    },
    {
        personalNumber: "2",
        password: 'halloP9!.',
        name: 'Pieter',
        email: 'nee@nee.nl',
        role: 'admin',
        location: 'Amsterdam',
        loggedIn: false
    },
];

beforeEach(async function () {
    const router = createRouter({
        history: createMemoryHistory(),
        routes: mockRoutes,
    })

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
            plugins: [i18n, router]
        }
    });

    await wrapper.vm.$router.isReady();
});

it('should render properly', function () {

    expect(wrapper.element.children.length,
        `main page starting with ${wrapper.element.tagname} has no child elements`)
        .toBeGreaterThan(0);
});

it('can log in', async function () {
    const onRouterPushSpi =
        jest.spyOn(wrapper.vm.$router,'push');
    const button = wrapper.get('#loginButton');
    const personalNumberField = wrapper.get('#personalNumberField');
    const passwordField = wrapper.get('#passwordField');

    expect(button.exists(),
        `Cannot find button with id loginButton`)
        .toBe(true);
    expect(button.element.disabled,
        `button with id loginButton has been disabled`)
        .toBeFalsy();

    expect(personalNumberField.exists(),
        `Cannot find button with id personalNumberField`)
        .toBe(true);
    expect(passwordField.exists(),
        `Cannot find button with id passwordField`)
        .toBe(true);

    await personalNumberField.setValue("1");
    await passwordField.setValue("Welkom01.");

    expect(personalNumberField.element.value).toBe(mockAccounts[0].personalNumber);
    expect(passwordField.element.value).toBe(mockAccounts[0].password);

    await button.trigger('click');
    expect(onRouterPushSpi).toHaveBeenCalledWith("/");

    await wrapper.vm.$nextTick();
});
