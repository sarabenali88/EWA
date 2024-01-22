/**
 * Test for the sign in functionalities
 *
 * @author Jasper Fernhout
 */
import {mount} from "@vue/test-utils";
import i18n from "@/i18n";
import {reactive} from "vue";
import SignInComponent from "@/components/SignInComponent";
import {AccountsAdaptor} from "@/services/AccountsAdaptor";
import {createMemoryHistory, createRouter} from "vue-router";
import WelcomeComponent from "@/components/WelcomeComponent";
import {SessionSbService} from "@/services/SessionSbService";
import signInComponent from "@/components/SignInComponent";
import {ref} from "vue";
import unknownRouteComponent from "@/components/UnknownRouteComponent";

let wrapper

jest.mock("@/services/AccountsAdaptor")

const mockRoutes = [
    {
        path: '/home',
        component: WelcomeComponent
    },
    {
        path: '/sign-in',
        component: signInComponent
    },
    {
        path: '/:catchAll(.*)',
        component: unknownRouteComponent
    }
];

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

/**
 * This is the test for the sign in component.
 * There are tests about how logging in is functional.
 *
 * @author Jasper Fernhout
 */
describe("SignInComponent", () => {
    beforeEach(async function () {
        const router = createRouter({
            history: createMemoryHistory(),
            routes: mockRoutes,
        })

        // Mocking the functions in the accounts service
        const accountsService = new AccountsAdaptor('http://localhost:8086/api');

        jest.spyOn(accountsService, "asyncFindAll").mockResolvedValue(mockAccounts);

        jest.spyOn(SessionSbService.prototype, "asyncSignIn").mockResolvedValue({
            personalNumber: mockAccounts[0].personalNumber,
            password: mockAccounts[0].password
        });

        jest.spyOn(accountsService, "asyncFindById").mockImplementation(async (personalNumber) => {
            const foundAccount = mockAccounts.find(account => account.personalNumber === personalNumber);
            return foundAccount ? foundAccount : null;
        });

        jest.spyOn(accountsService, "asyncSave").mockImplementation(async (account) => {
            if (account.personalNumber === 0) {
                account.personalNumber = mockAccounts.length + 1;
                mockAccounts.push(account);
                return account;
            } else {
                for (let i = 0; i < mockAccounts.length; i++) {
                    if (mockAccounts[i].personalNumber === account.personalNumber) {
                        mockAccounts[i] = account;
                    }
                }
                return account;
            }
        });

        jest.spyOn(accountsService, "asyncDeleteById").mockResolvedValue({success: true});

        // Mocking the sessionService
        const reactiveSessionService = ref(new SessionSbService('http://localhost:8086/api', 'JWT_MARKT'));

        wrapper = mount(SignInComponent, {
            global: {
                provide: {
                    "accountsService": reactive(accountsService),
                    "sessionService": reactiveSessionService,
                },
                plugins: [i18n, router]
            }
        });

        // Use a spy on the display alert so that it can be used later on
        jest.spyOn(wrapper.vm, 'displayAlert');

        await wrapper.vm.$router.isReady();
    });

    it('should render properly', function () {
        expect(wrapper.element.children.length,
            `main page starting with ${wrapper.element.tagname} has no child elements`)
            .toBeGreaterThan(0);
        expect(wrapper.exists()).toBe(true);
    });

    it('Elements check signInContainer', function () {
        const button = wrapper.get('#loginButton');
        const personalNumberField = wrapper.get('#personalNumberField');
        const passwordField = wrapper.get('#passwordField');
        const switchButton = wrapper.get('#loginPasswordChangeBtn');

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
        expect(switchButton.exists()).toBe(true);
    });

    it('Log in test with right values', async function () {
        const onRouterPushSpy = jest.spyOn(wrapper.vm.$router, 'push');
        const onSignInSpy = jest.spyOn(wrapper.vm.sessionService, 'asyncSignIn');
        const button = wrapper.get('#loginButton');
        const personalNumberField = wrapper.get('#personalNumberField');
        const passwordField = wrapper.get('#passwordField');

        // Value implementation
        await personalNumberField.setValue(1);
        await passwordField.setValue("Welkom01.");

        expect(personalNumberField.element.value).toBe(mockAccounts[0].personalNumber.toString());
        expect(passwordField.element.value).toBe(mockAccounts[0].password);

        await button.trigger('click');

        await wrapper.vm.$nextTick();

        expect(onSignInSpy).toHaveBeenCalledWith(
            mockAccounts[0].personalNumber.toString(),
            mockAccounts[0].password
        );

        expect(onRouterPushSpy).toHaveBeenCalledWith(mockRoutes[0].path);
    });

    it('Log in test with wrong values', async function () {
        const onRouterPushSpy = jest.spyOn(wrapper.vm.$router, 'push');
        const onSignInSpy = jest.spyOn(wrapper.vm.sessionService, 'asyncSignIn').mockResolvedValue(null);
        const button = wrapper.get('#loginButton');
        const personalNumberField = wrapper.get('#personalNumberField');
        const passwordField = wrapper.get('#passwordField');

        // Value implementation
        await personalNumberField.setValue(1);
        await passwordField.setValue("IncorrectPassword123");

        expect(personalNumberField.element.value).toBe(mockAccounts[0].personalNumber.toString());
        expect(passwordField.element.value).not.toBe(mockAccounts[0].password);

        await button.trigger('click');

        await wrapper.vm.$nextTick();

        expect(onSignInSpy).toHaveBeenCalledWith(
            "1",
            "IncorrectPassword123"
        );
        expect(wrapper.vm.displayAlert).toHaveBeenCalledWith(wrapper.vm.$t('signIn.wrongPersMessage'));

        expect(onRouterPushSpy).not.toHaveBeenCalledWith(mockRoutes[0].path);
    });

    it('Should switch containers when the password change button is pressed ', async function () {
        const switchContainerButton = wrapper.get('#loginPasswordChangeBtn');
        await switchContainerButton.trigger('click');

        expect(wrapper.vm.visible).toBe(false);
    });

    it('Elements check for the passwordChangeContainer ', function () {
        const personalNumberPC = wrapper.get('#personalNumberPC');
        const emailPC = wrapper.get('#emailPC');
        const newPasswordPC = wrapper.get('#newPasswordPC');
        const passwordRepeatPC = wrapper.get('#passwordRepeatPC');
        const changePasswordButton = wrapper.get('#confirmPasswordChangeBtn');

        expect(changePasswordButton.exists()).toBe(true);
        expect(personalNumberPC.exists()).toBe(true);
        expect(emailPC.exists()).toBe(true);
        expect(newPasswordPC.exists()).toBe(true);
        expect(passwordRepeatPC.exists()).toBe(true);
    });

    it('Password change with right values', async function () {
        // Click the button to switch visibility
        const spyAsyncSave = jest.spyOn(wrapper.vm.accountsService, "asyncSave");
        const switchContainerButton = wrapper.get('#loginPasswordChangeBtn');
        const newPassword = "NewPassword123";

        await switchContainerButton.trigger('click');

        await wrapper.vm.$nextTick();

        // Elements
        const changePasswordButton = wrapper.get('#confirmPasswordChangeBtn');
        const personalNumberPC = wrapper.get('#personalNumberPC');
        const emailPC = wrapper.get('#emailPC');
        const newPasswordPC = wrapper.get('#newPasswordPC');
        const passwordRepeatPC = wrapper.get('#passwordRepeatPC');

        // Entering values
        await personalNumberPC.setValue(mockAccounts[0].personalNumber);
        await emailPC.setValue(mockAccounts[0].email);
        await newPasswordPC.setValue(newPassword);
        await passwordRepeatPC.setValue(newPassword);

        const updatedAccount = {
            personalNumber: mockAccounts[0].personalNumber,
            password: newPassword,
            name: mockAccounts[0].name,
            email: mockAccounts[0].email,
            role: mockAccounts[0].role,
            location: mockAccounts[0].location,
            loggedIn: false
        };

        await changePasswordButton.trigger('click');

        await wrapper.vm.$nextTick();

        expect(spyAsyncSave).toHaveBeenCalledWith(updatedAccount);

        expect(mockAccounts[0].password).toBe("NewPassword123");
    });

    it('Password change with wrong repeated password', async function () {
        // Click the button to switch visibility
        const spyAsyncSave = jest.spyOn(wrapper.vm.accountsService, "asyncSave");
        const switchContainerButton = wrapper.get('#loginPasswordChangeBtn');
        const newPassword = "NewPassword123";

        await switchContainerButton.trigger('click');

        await wrapper.vm.$nextTick();

        // Elements check
        const changePasswordButton = wrapper.get('#confirmPasswordChangeBtn');
        const personalNumberPC = wrapper.get('#personalNumberPC');
        const emailPC = wrapper.get('#emailPC');
        const newPasswordPC = wrapper.get('#newPasswordPC');
        const passwordRepeatPC = wrapper.get('#passwordRepeatPC');

        // Entering values
        await personalNumberPC.setValue(mockAccounts[0].personalNumber);
        await emailPC.setValue(mockAccounts[0].email);
        await newPasswordPC.setValue(newPassword);
        await passwordRepeatPC.setValue("WrongPassword456");

        const updatedAccount = {
            personalNumber: mockAccounts[0].personalNumber,
            password: newPassword,
            name: mockAccounts[0].name,
            email: mockAccounts[0].email,
            role: mockAccounts[0].role,
            location: mockAccounts[0].location,
            loggedIn: false
        };

        expect(changePasswordButton.exists()).toBe(true);

        // Triggering the password change
        await changePasswordButton.trigger('click');

        await wrapper.vm.$nextTick();

        // Ensure that spyAsyncSave is not called with the updatedAccount
        expect(spyAsyncSave).not.toHaveBeenCalledWith(updatedAccount);

        // Confirm that the password in mockAccounts has not changed
        expect(wrapper.vm.displayAlert).toHaveBeenCalledWith(wrapper.vm.$t('signIn.passwordWrongRepeat'));
    });

    it('Password change with wrong personalNumber', async function () {
        // Click the button to switch visibility
        const spyAsyncSave = jest.spyOn(wrapper.vm.accountsService, "asyncSave");
        const switchContainerButton = wrapper.get('#loginPasswordChangeBtn');
        const newPassword = "NewPassword123";

        await switchContainerButton.trigger('click');

        await wrapper.vm.$nextTick();

        // Elements check
        const changePasswordButton = wrapper.get('#confirmPasswordChangeBtn');
        const personalNumberPC = wrapper.get('#personalNumberPC');
        const emailPC = wrapper.get('#emailPC');
        const newPasswordPC = wrapper.get('#newPasswordPC');
        const passwordRepeatPC = wrapper.get('#passwordRepeatPC');

        // Entering values
        await personalNumberPC.setValue(8);
        await emailPC.setValue(mockAccounts[0].email);
        await newPasswordPC.setValue(newPassword);
        await passwordRepeatPC.setValue(newPassword);

        const updatedAccount = {
            personalNumber: mockAccounts[0].personalNumber,
            password: newPassword,
            name: mockAccounts[0].name,
            email: mockAccounts[0].email,
            role: mockAccounts[0].role,
            location: mockAccounts[0].location,
            loggedIn: false
        };

        expect(changePasswordButton.exists()).toBe(true);

        // Triggering the password change
        await changePasswordButton.trigger('click');

        await wrapper.vm.$nextTick();

        // Ensure that spyAsyncSave is not called with the updatedAccount
        expect(spyAsyncSave).not.toHaveBeenCalledWith(updatedAccount);

        // Confirm that the password in mockAccounts has not changed
        expect(wrapper.vm.displayAlert).toHaveBeenCalledWith(wrapper.vm.$t('signIn.wrongPersonalNumber'));
    });
});