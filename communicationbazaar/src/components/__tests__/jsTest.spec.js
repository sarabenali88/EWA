import {Account} from "@/models/Account";

let account1, account2;

beforeEach(function () {
    account1 = new Account(1, 1, 1, 1, 1, 1,false)
    account2 = Account.CreateSampleAccount(account1.personalNumber+1)
})

it('should create a proper sample account', function () {
    expect(account2.personalNumber,
        'Account2 id was not set properly')
        .toBe(account1.personalNumber+1);
    expect(account2.name,
        'account2 name was not set properly')
        .toBeTruthy();
});
