import { PublicPagePage } from './app.po';

describe('public-page App', () => {
  let page: PublicPagePage;

  beforeEach(() => {
    page = new PublicPagePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
